package hospital.controller;
import hospital.model.entity.Visit;
import hospital.model.entity.enums.VisitPrice;
import hospital.model.service.*;
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
public class VisitController implements Initializable {
    @FXML
    private TextField idText,doctorIdText,patientIdText,timeShiftIdText,paymentIdText,searchDoctorId,searchPatientId;

    @FXML
    private ComboBox<VisitPrice> priceCombo;

    @FXML
    private Button saveButton,editButton,deleteButton;

    @FXML
    private TableView<Visit> visitTable;

    @FXML
    private TableColumn<Visit,Integer> idColumn,doctorIdColumn,patientIdColumn,timeShiftIdColumn,paymentIdColumn;

    @FXML
    private TableColumn<Visit,VisitPrice> priceColumn;
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
                Visit visit=
                        Visit
                                .builder()
                                .doctor(DoctorService.getService().findById(Integer.parseInt(doctorIdText.getText())))
                                .patient(PatientService.getService().findById(Integer.parseInt(patientIdText.getText())))
                                .timeShift(TimeShiftService.getService().findById(Integer.parseInt(timeShiftIdText.getText())))
                                .payment(PaymentService.getService().findById(Integer.parseInt(paymentIdText.getText())))
                                .price(priceCombo.getSelectionModel().getSelectedIndex())
                                .build();
                VisitService.getService().save(visit);
                log.info("Visit Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + visit, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Visit Save Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Visit Save Failed " + e.getMessage(), ButtonType.OK);
                alert.show();            }
        });

        editButton.setOnAction((event) -> {
            try {
                Visit visit =
                        Visit
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .doctor(DoctorService.getService().findById(Integer.parseInt(doctorIdText.getText())))
                                .patient(PatientService.getService().findById(Integer.parseInt(patientIdText.getText())))
                                .timeShift(TimeShiftService.getService().findById(Integer.parseInt(timeShiftIdText.getText())))
                                .payment(PaymentService.getService().findById(Integer.parseInt(paymentIdText.getText())))
                                .price(priceCombo.getSelectionModel().getSelectedIndex())
                                .build();
                VisitService.getService().edit(visit);
                log.info("Visit Edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + visit, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Visit Edit Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Visit Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction((event) -> {
            try {
                VisitService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("Visit Deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Visit Delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Visit Delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        searchDoctorId.setOnKeyReleased((event) -> searchByDoctorId());
        searchPatientId.setOnKeyReleased((event) -> searchByPatientId());


        visitTable.setOnMouseReleased((event) -> selectFromTable());

        visitTable.setOnKeyReleased((event) -> selectFromTable());

    }
    private void resetForm() throws Exception {
        idText.clear();
        doctorIdText.clear();
        patientIdText.clear();
        timeShiftIdText.clear();
        paymentIdText.clear();
        searchDoctorId.clear();
        searchPatientId.clear();



        for (VisitPrice visitPrice : VisitPrice.values()) {
            priceCombo.getItems().add(visitPrice);
        }
        priceCombo.getSelectionModel().select(0);

        showDateOnTable(VisitService.getService().findAll());
    }

    private void showDateOnTable(List<Visit> visitList) {
        ObservableList<Visit> observableList = FXCollections.observableList(visitList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        doctorIdColumn.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        timeShiftIdColumn.setCellValueFactory(new PropertyValueFactory<>("timeShiftId"));
        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


        visitTable.setItems(observableList);
    }

    public void selectFromTable() {
        try {
            Visit visit = visitTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(visit.getId()));
            doctorIdText.setText(String.valueOf(visit.getDoctor().getId()));
            patientIdText.setText(String.valueOf(visit.getPatient().getId()));
            timeShiftIdText.setText(String.valueOf(visit.getTimeShift().getId()));
            paymentIdText.setText(String.valueOf(visit.getPayment().getId()));
            priceCombo.getSelectionModel().select(0);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

    public void searchByDoctorId() {
        try {
            showDateOnTable(VisitService.getService().findByDoctorId(Integer.parseInt(searchDoctorId.getText())));
            log.info("visits findByDoctorId " + searchDoctorId.getText() + " Successfully");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            log.info("visits findByDoctorId " + searchDoctorId.getText() + " error" + e.getMessage());
            alert.show();
        }
    }

    public void searchByPatientId() {
        try {
            showDateOnTable(VisitService.getService().findByPatientId(Integer.parseInt(searchPatientId.getText())));
            log.info("visits findByPatientId " + searchPatientId.getText() + " Successfully");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            log.info("visits findByPatientId " + searchPatientId.getText() + " error" + e.getMessage());
            alert.show();
        }
    }
}
