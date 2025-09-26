package hospital.controller;

import hospital.model.entity.Doctor;
import hospital.model.entity.enums.Specialty;
import hospital.model.entity.enums.VisitPrice;
import hospital.model.service.DoctorService;
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
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class DoctorController implements Initializable {
    @FXML
    private TextField idText, userIdText, searchNameText, searchFamilyText;

    @FXML
    private ComboBox<Specialty> specialityCombo;

    @FXML
    private ComboBox<VisitPrice> priceCombo;

    @FXML
    private Button saveButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Doctor> doctorTable;

    @FXML
    private TableColumn<Doctor, Integer> idColumn;

    @FXML
    private TableColumn<Doctor, String> nameColumn;

    @FXML
    private TableColumn<Doctor, String> familyColumn;

    @FXML
    private TableColumn<Doctor, Specialty> specialityColumn;

    @FXML
    private TableColumn<Doctor, VisitPrice> priceColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }

        saveButton.setOnAction(event -> {
            try {
                Doctor doctor =
                        Doctor
                                .builder()
                                .user(UserService.service.findById(Integer.parseInt(userIdText.getText())))
                                .specialty(specialityCombo.getSelectionModel().getSelectedItem())
                                .price(priceCombo.getSelectionModel().getSelectedItem().getPrice())
                                .build();
                DoctorService.getService().save(doctor);
                log.info("Doctor Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + doctor, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Doctor Save Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction(event -> {
            try {
                Doctor doctor =
                        Doctor
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .user(UserService.service.findById(Integer.parseInt(userIdText.getText())))
                                .specialty(specialityCombo.getSelectionModel().getSelectedItem())
                                .price(priceCombo.getSelectionModel().getSelectedItem().getPrice())
                                .build();
                DoctorService.getService().edit(doctor);
                log.info("Doctor edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "edited Successfully\n" + doctor, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Doctor edited Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction(event -> {
            try {
                DoctorService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("Doctor Deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "delete Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            }catch (Exception e) {
                log.error("Doctor Delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Doctor Delete Failed!!!", ButtonType.OK);
                alert.show();
            }
        });

        doctorTable.setOnKeyReleased((event) -> selectFromTable());

        doctorTable.setOnMouseReleased((event) -> selectFromTable());

        searchNameText.setOnKeyReleased((event) -> searchByNameAndFamily());

        searchFamilyText.setOnKeyReleased((event) -> searchByNameAndFamily());

    }

    private void showDataOnTable(List<Doctor> doctorList) {
        ObservableList<Doctor> observableList = FXCollections.observableArrayList(doctorList);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getUser().getName())
        );
        familyColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getUser().getFamily())
        );
        specialityColumn.setCellValueFactory(new PropertyValueFactory<>("specialty"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        doctorTable.setItems(observableList);

    }

    private void resetForm() throws Exception {
        idText.clear();
        userIdText.clear();
        searchNameText.clear();
        searchFamilyText.clear();
        specialityCombo.getItems().clear();
        priceCombo.getItems().clear();

        for (Specialty specialty : Specialty.values()) {
            specialityCombo.getItems().add(specialty);
        }
        for (VisitPrice price : VisitPrice.values()) {
            priceCombo.getItems().add(price);
        }
        specialityCombo.getSelectionModel().select(0);
        priceCombo.getSelectionModel().select(0);

        showDataOnTable(DoctorService.getService().findAll());
    }

    private void selectFromTable() {
        try {
            Doctor doctor = doctorTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(doctor.getId()));
            userIdText.setText(String.valueOf(doctor.getUser().getId()));
            specialityCombo.getSelectionModel().select(doctor.getSpecialty());
            priceCombo.getSelectionModel().select(doctor.getPrice());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

    private void searchByNameAndFamily() {
        try {
            showDataOnTable(DoctorService.getService().findByNameAndFamily(searchNameText.getText(), searchFamilyText.getText()));
            log.info("Doctors FindByNameAndFamily :" + searchNameText.getText() + " " + searchFamilyText.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching Data !!!", ButtonType.OK);
            alert.show();
            log.error("Doctors FindNameFamily " + searchNameText.getText() + " " + searchFamilyText.getText() + " Failed " + e.getMessage());
        }
    }
}

