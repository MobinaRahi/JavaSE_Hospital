package hospital.controller;

import hospital.model.entity.Drug;
import hospital.model.entity.Payable;
import hospital.model.entity.Prescription;
import hospital.model.entity.Visit;
import hospital.model.service.PrescriptionService;
import hospital.model.service.VisitService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import lombok.extern.log4j.Log4j2;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class PrescriptionController implements Initializable {
    @FXML
    private TextField visitIdText;

    @FXML
    private Button drugDeleteButton;

    @FXML
    private Button prescriptionSaveButton;

    @FXML
    private Button prescriptionEditButton;

    @FXML
    private Button prescriptionDeleteButton;

    @FXML
    private Button addDrugButton;

    @FXML
    private Button payButton;

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
            try {
                List<Drug> drugList = new ArrayList<>();
                Prescription prescription =
                        Prescription
                                .builder()
                                .visit(VisitService.getService().findById(Integer.parseInt(visitIdText.getText())))
                                .price(0)
                                .drugList(drugList)
                                .build();
                PrescriptionService.getService().save(prescription);
                log.info("prescription saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "saved Successfully\n" + prescription, ButtonType.OK);
                alert.show();
                resetForm();

            } catch (Exception e) {

                log.error("prescription Save Failed {}", e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
                alert.show();
            }
        });

        prescriptionEditButton.setOnAction(event -> {
            try {
                Prescription selectedPrescription = prescriptionTable.getSelectionModel().getSelectedItem();
                Prescription prescription =
                        Prescription
                                .builder()
                                .id(selectedPrescription.getId())
                                .visit(VisitService.getService().findById(Integer.parseInt(visitIdText.getText())))
                                .drugList(selectedPrescription.getDrugList())
                                .price(selectedPrescription.getPrice())
                                .build();

                PrescriptionService.getService().edit(prescription);
                log.info("prescription edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "edited Successfully\n" + prescription, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("prescription Save Failed {}", e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error updating prescription!", ButtonType.OK);
                alert.show();
            }
        });

        prescriptionDeleteButton.setOnAction(event -> {
            try {
                Prescription selectedPrescription = prescriptionTable.getSelectionModel().getSelectedItem();
                PrescriptionService.getService().delete(selectedPrescription.getId());
                log.info("prescription Deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "delete Successfully\n" + selectedPrescription.getId(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("prescription Delete Failed {}", e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "prescription Delete Failed!!!", ButtonType.OK);
                alert.show();
            }
        });

        drugDeleteButton.setOnAction(event -> {
            try {
                Prescription selectedPrescription = prescriptionTable.getSelectionModel().getSelectedItem();
                Drug selectedDrug = drugListTable.getSelectionModel().getSelectedItem();

                if (selectedPrescription == null || selectedDrug == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a prescription and a drug!", ButtonType.OK);
                    alert.show();
                    return;
                }

                PrescriptionService.getService().removeDrugFromPrescription(selectedPrescription.getId(), selectedDrug.getId());

                Prescription updatedPrescription = PrescriptionService.getService().findById(selectedPrescription.getId());

                drugListTable.setItems(FXCollections.observableArrayList(updatedPrescription.getDrugList()));

                int selectedId = selectedPrescription.getId();
                List<Prescription> allPrescriptions = PrescriptionService.getService().findAll();
                showDataOnTable(allPrescriptions);
                for (Prescription p : allPrescriptions) {
                    if (p.getId() == selectedId) {
                        prescriptionTable.getSelectionModel().select(p);
                        prescriptionTable.scrollTo(p);
                        break;
                    }
                }

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
            if (prescription == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "please select a prescription", ButtonType.OK);
                alert.show();
                return;
            }

            visitIdText.setText(String.valueOf(prescription.getVisit().getId()));


            List<Drug> drugList = prescription.getDrugList();
            ObservableList<Drug> drugObservableList = FXCollections.observableArrayList(drugList);
            drugIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            drugNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            drugPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            drugQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            drugListTable.setItems(drugObservableList);

            payButton.setOnAction(event -> {
                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PaymentView.fxml"));
                    Parent root = loader.load();
                    PaymentController paymentController = loader.getController();
                    paymentController.setPayable(prescription);
                    stage.setTitle("Payment");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                    alert.show();
                }

                ;

            });


            addDrugButton.setOnAction(e -> {
                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddDrugView.fxml"));
                    Parent root = loader.load();
                    AddDrugController addDrugController = loader.getController();
                    addDrugController.setPrescriptionId(prescription.getId());
                    addDrugController.setParentController(this);
                    stage.setTitle("Add Drugs");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                    alert.show();
                }
            });

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error updating prescription!", ButtonType.OK);
            alert.show();
        }
    }

    private void resetForm() throws Exception {
        if (prescriptionTable.getSelectionModel().getSelectedItem() != null) {
            prescriptionTable.getSelectionModel().select(prescriptionTable.getSelectionModel().getSelectedItem());
        }
        visitIdText.clear();
        showDataOnTable(PrescriptionService.getService().findAll());
    }

    public void refreshPrescriptionData() {
        try {
            Prescription selected = prescriptionTable.getSelectionModel().getSelectedItem();
            Integer selectedId = (selected != null) ? selected.getId() : null;

            List<Prescription> allPrescriptions = PrescriptionService.getService().findAll();
            showDataOnTable(allPrescriptions);

            if (selectedId != null) {
                for (Prescription p : allPrescriptions) {
                    if (p.getId() == (selectedId)) {
                        prescriptionTable.getSelectionModel().select(p);

                        ObservableList<Drug> drugObservableList =
                                FXCollections.observableArrayList(p.getDrugList());
                        drugListTable.setItems(drugObservableList);

                        break;
                    }
                }
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error refreshing prescription data!", ButtonType.OK);
            alert.show();
        }
    }

}
