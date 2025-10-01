package hospital.controller;


import hospital.model.entity.Employee;
import hospital.model.service.EmployeeService;
import hospital.model.service.UserService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;

import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class EmployeeController implements Initializable {
    @FXML
    private TextField idText,userIdText,startTimeText,endTimeText,searchUserIdText;

    @FXML
    private Button saveButton,editButton,deleteButton;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee,Integer> idColumn;

    @FXML
    private TableColumn<Employee,String> userIdColumn;

    @FXML
    private TableColumn<Employee, LocalTime> startTimeColumn,endTimeColumn;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }

        saveButton.setOnAction(event -> {
            try {
                Employee employee =
                        Employee
                                .builder()
                                .user(UserService.getService().findById(Integer.parseInt(userIdText.getText())))
                                .startTime(LocalTime.parse(startTimeText.getText()))
                                .endTime(LocalTime.parse(endTimeText.getText()))
                                .build();
                EmployeeService.getService().save(employee);
                log.info("Employee Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + employee, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Employee Save Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Employee Save Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction(event -> {
            try {
                Employee employee =
                        Employee
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .user(UserService.getService().findById(Integer.parseInt(userIdText.getText())))
                                .startTime(LocalTime.parse(startTimeText.getText()))
                                .endTime(LocalTime.parse(endTimeText.getText()))
                                .build();
                EmployeeService.getService().edit(employee);
                log.info("Employee Edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + employee, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Employee Edit Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Employee Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }

        });

        deleteButton.setOnAction((event) -> {
            try {
                EmployeeService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("Employee Deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Employee Delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Employee" +
                        " Delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        employeeTable.setOnMouseReleased((event) -> selectFromTable());

        employeeTable.setOnKeyReleased((event) -> selectFromTable());

        searchUserIdText.setOnKeyReleased((event) -> searchByUserId());
        searchUserIdText.setOnKeyReleased((event) -> searchByUserId());
    }

    private void resetForm() throws Exception {
        idText.clear();
        userIdText.clear();
        startTimeText.clear();
        endTimeText.clear();
        showDateOnTable(EmployeeService.getService().findAll());
    }

    private void showDateOnTable(List<Employee> employeeList) {
        ObservableList<Employee> observableList = FXCollections.observableList(employeeList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdColumn.setCellValueFactory(cellData->
                new SimpleStringProperty(cellData.getValue().getUser().getName()));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        employeeTable.setItems(observableList);
    }

    public void selectFromTable() {
        try {
            Employee employee = employeeTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(employee.getId()));
            userIdText.setText(String.valueOf(employee.getUser().getId()));
            startTimeText.setText(String.valueOf(employee.getStartTime()));
            endTimeText.setText(String.valueOf(employee.getEndTime()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

    public void searchByUserId() {
        try {
            showDateOnTable(EmployeeService.getService().findByUserId(Integer.parseInt(searchUserIdText.getText())));
            log.info("employee findEmployeeByUserId " + searchUserIdText.getText() + " Successfully");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            log.info("employee findEmployeeByUserId " + searchUserIdText.getText() + " error" + e.getMessage());
            alert.show();
        }
    }
}
