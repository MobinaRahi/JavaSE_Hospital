package hospital.controller;

import hospital.model.entity.Patient;
import hospital.model.service.PatientService;
import hospital.model.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


@Log4j2
public class PatientController implements Initializable {

    @FXML
    private TextField idText,userIdText,searchUserIdText;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<Patient> patientTable;

    @FXML
    private TableColumn<Patient, Integer> idColumn, userIdColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data", ButtonType.OK);
            alert.show();
        }

        saveButton.setOnAction(event -> {
            try {
                Patient patient =
                        Patient
                                .builder()
                                .user(UserService.getService().findById(Integer.parseInt(userIdText.getText())))
                                .build();
                PatientService.getService().save(patient);
                log.info("Patient save  Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + patient, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("patient Save Failed" + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "patient save Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction(event -> {
            try {
                Patient patient =
                        Patient
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .user(UserService.getService().findById(Integer.parseInt(userIdText.getText())))
                                .build();
                PatientService.getService().edit(patient);
                log.info("Patient Edit Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edit Successfully\n" + patient, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Patient Edit Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Patient Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction((event) -> {
            try {
                PatientService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("Patient Delete Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Patient Delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Person Delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        patientTable.setOnMouseReleased((event) -> selectFromTable());
        patientTable.setOnKeyReleased((event) -> selectFromTable());

        searchUserIdText.setOnKeyReleased((event) -> searchByUserId());
    }

    private void resetForm() throws Exception {
        idText.clear();
        userIdText.clear();

        showDateOnTable(PatientService.getService().findAll());
    }

    private void showDateOnTable(List<Patient> patientList) {
        ObservableList<Patient> ObservableList = FXCollections.observableList(patientList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        patientTable.setItems(ObservableList);
    }

    private void selectFromTable() {
        try {
            Patient patient = patientTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(patient.getId()));
            userIdText.setText(String.valueOf(patient.getUser().getId()));

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

    public  void searchByUserId() {
        try {
            showDateOnTable(PatientService.getService().findByUserId(Integer.parseInt(userIdText.getText())));
            log.info("patient findPatientByUserId " + searchUserIdText.getText() + " Successfully");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            log.info("patient findPatientByUserId " + searchUserIdText.getText() + " Failed" + e.getMessage());
            alert.show();
        }
    }
}