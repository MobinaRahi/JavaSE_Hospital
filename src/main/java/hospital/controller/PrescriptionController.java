package hospital.controller;

import hospital.model.entity.Drug;
import hospital.model.entity.Prescription;
import hospital.model.service.PrescriptionService;
import hospital.model.service.VisitService;
import javafx.beans.property.SimpleObjectProperty;
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
public class PrescriptionController implements Initializable {
    @FXML
    private TextField idText, visitIdText, priceText;

    @FXML
    private Button drugEditButton;

    @FXML
    private Button drugDeleteButton;

    @FXML
    private Button prescriptionSaveButton;

    @FXML
    private Button prescriptionEditButton;

    @FXML
    private Button prescriptionDeleteButton;

    @FXML
    private TableView<Prescription> prescriptionTable;

    @FXML
    private TableView<Drug> drugListTable;

    @FXML
    private TableColumn<Prescription, Integer> idColumn;

    @FXML
    private TableColumn<Prescription, Integer> visitIdColumn;

    @FXML
    private TableColumn<Prescription, Integer> patientIdColumn;

    @FXML
    private TableColumn<Prescription, String> prescriptionNameColumn;

    @FXML
    private TableColumn<Prescription, String> prescriptionFamilyColumn;

    @FXML
    private TableColumn<Prescription, Number> priceColumn;

    @FXML
    private TableColumn<Drug, Integer> drugIdColumn;

    @FXML
    private TableColumn<Drug, String> drugNameColumn;

    @FXML
    private TableColumn<Drug, String> drugPriceColumn;

    @FXML
    private TableColumn<Drug, String> drugQuantityColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
        prescriptionSaveButton.setOnAction(event -> {
        });

        prescriptionEditButton.setOnAction(event -> {
            try {
                Prescription selectedPrescription = prescriptionTable.getSelectionModel().getSelectedItem();
                Prescription prescription =
                        Prescription
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .visit(VisitService.getService().findById(Integer.parseInt(visitIdText.getText())))
                                .drugList(selectedPrescription.getDrugList())
                                .price(Double.parseDouble(priceText.getText()))
                                .build();

                PrescriptionService.getService().edit(prescription);
                log.info("prescription edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "edited Successfully\n" + prescription, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Error updating prescription: " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error updating prescription!", ButtonType.OK);
                alert.show();
            }
        });

        prescriptionDeleteButton.setOnAction(event -> {
            try {
                PrescriptionService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("prescription Deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "delete Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("prescription Delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "prescription Delete Failed!!!", ButtonType.OK);
                alert.show();
            }
        });

        drugDeleteButton.setOnAction(event -> {
            try {
                Prescription prescription = prescriptionTable.getSelectionModel().getSelectedItem();
                Drug selectDrug = drugListTable.getSelectionModel().getSelectedItem();

                prescription.getDrugList().remove(selectDrug);

                ObservableList<Drug> updateList = FXCollections.observableArrayList(prescription.getDrugList());
                priceText.setText(String.valueOf(prescription.getPrice()));
                prescription.setPrice(prescription.getPrice());
                drugListTable.setItems(updateList);

                PrescriptionService.getService().removeDrugFromPrescription(prescription.getId(), selectDrug.getId());
                Prescription updatedPrescription = PrescriptionService.getService().findById(prescription.getId());
                drugListTable.setItems(FXCollections.observableArrayList(updatedPrescription.getDrugList()));
                priceText.setText(String.valueOf(updatedPrescription.getPrice()));
                showDataOnTable(PrescriptionService.getService().findAll());
                log.info("Drug removed and prescription updated successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Drug removed and prescription updated!", ButtonType.OK);
                alert.show();

            } catch (Exception e) {
                log.error("Error updating prescription: " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error updating prescription!", ButtonType.OK);
                alert.show();
            }
        });

        prescriptionTable.setOnKeyReleased((event) -> selectFromTable());

        prescriptionTable.setOnMouseReleased((event) -> selectFromTable());

    }

    private void showDataOnTable(List<Prescription> prescriptionList) {
        ObservableList<Prescription> observableList = FXCollections.observableArrayList(prescriptionList);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        visitIdColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getVisit().getId())
        );
        patientIdColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getVisit().getPatient().getId())
        );
        prescriptionNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getVisit().getPatient().getUser().getName())
        );
        prescriptionFamilyColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getVisit().getPatient().getUser().getFamily())
        );
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        prescriptionTable.setItems(observableList);

    }

    private void selectFromTable() {
        try {
            Prescription prescription = prescriptionTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(prescription.getId()));
            visitIdText.setText(String.valueOf(prescription.getVisit().getId()));
            priceText.setText(String.valueOf(prescription.getPrice()));

            List<Drug> drugList = prescription.getDrugList();
            ObservableList<Drug> drugObservableList = FXCollections.observableArrayList(drugList);
            drugIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            drugNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            drugPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            drugQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            drugListTable.setItems(drugObservableList);

        } catch (Exception e) {
            log.error("Error updating prescription: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error updating prescription!", ButtonType.OK);
            alert.show();
        }
    }

    private void resetForm() throws Exception {
        idText.clear();
        visitIdText.clear();
        priceText.clear();

        showDataOnTable(PrescriptionService.getService().findAll());
    }
}
