package hospital.controller;

import hospital.model.entity.TimeShift;
import hospital.model.service.DoctorService;
import hospital.model.service.MedicalService;
import hospital.model.service.TimeShiftService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.net.URL;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class TimeShiftController implements Initializable {
    @FXML
    private TextField idText, doctorIdText, medicalIdText, searchDoctorIdText;

    @FXML
    private DatePicker startDateTime, endDateTime;

    @FXML
    private Button saveButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<TimeShift> timeShiftTable;

    @FXML
    private TableColumn<TimeShift, Integer> idColumn;

    @FXML
    private TableColumn<TimeShift, String> doctorNameColumn;

    @FXML
    private TableColumn<TimeShift, String> medicalTitleColumn;

    @FXML
    private TableColumn<TimeShift, String> startDateTimeColumn;

    @FXML
    private TableColumn<TimeShift, String> endDateTimeColumn;


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

                TimeShift timeShift =
                        TimeShift
                                .builder()
                                .doctor(DoctorService.getService().findById(Integer.parseInt(doctorIdText.getText())))
                                .medical(MedicalService.getService().findById(Integer.parseInt(medicalIdText.getText())))
                                .startDateTime(startDateTime.getValue().atStartOfDay())
                                .endDateTime(endDateTime.getValue().atStartOfDay())
                                .build();
                TimeShiftService.getService().save(timeShift);
                log.info("TimeShift Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + timeShift, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("TimeShift Save Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction(event -> {
            try {
                TimeShift timeShift =
                        TimeShift
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .doctor(DoctorService.getService().findById(Integer.parseInt(doctorIdText.getText())))
                                .medical(MedicalService.getService().findById(Integer.parseInt(medicalIdText.getText())))
                                .startDateTime(startDateTime.getValue().atStartOfDay())
                                .endDateTime(endDateTime.getValue().atStartOfDay())
                                .build();
                TimeShiftService.getService().edit(timeShift);
                log.info("TimeShift edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "edited Successfully\n" + timeShift, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("TimeShift edited Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction(event -> {
            try {
                TimeShiftService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("Doctor Deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "delete Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("TimeShift Delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "TimeShift Delete Failed!!!", ButtonType.OK);
                alert.show();
            }
        });

        timeShiftTable.setOnKeyReleased((event) -> selectFromTable());

        timeShiftTable.setOnMouseReleased((event) -> selectFromTable());

        searchDoctorIdText.setOnKeyReleased((event) -> searchByDoctorId());

        searchDoctorIdText.setOnKeyReleased((event) -> searchByDoctorId());

    }

    private void showDataOnTable(List<TimeShift> timeShiftList) {
        ObservableList<TimeShift> observableList = FXCollections.observableArrayList(timeShiftList);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        doctorNameColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue() != null
                    && cellData.getValue().getDoctor() != null
                    && cellData.getValue().getDoctor().getUser() != null) {
                return new SimpleStringProperty(cellData.getValue().getDoctor().getUser().getName());
            } else {
                return new SimpleStringProperty("نامشخص");
            }
        });

        medicalTitleColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue() != null && cellData.getValue().getMedical() != null) {
                return new SimpleStringProperty(cellData.getValue().getMedical().getTitle());
            } else {
                return new SimpleStringProperty("نامشخص");
            }
        });

        startDateTimeColumn.setCellValueFactory(cellData -> {
            LocalDateTime start = cellData.getValue().getStartDateTime();
            return new SimpleStringProperty(start != null ? start.toString() : "نامشخص");
        });

        endDateTimeColumn.setCellValueFactory(cellData -> {
            LocalDateTime end = cellData.getValue().getEndDateTime();
            return new SimpleStringProperty(end != null ? end.toString() : "نامشخص");
        });

        timeShiftTable.setItems(observableList);
    }

    private void resetForm() throws Exception {
        idText.clear();
        doctorIdText.clear();
        medicalIdText.clear();
        searchDoctorIdText.clear();
        startDateTime.setValue(LocalDate.now());
        endDateTime.setValue(LocalDate.now());
        showDataOnTable(TimeShiftService.getService().findAll());
    }

    private void selectFromTable() {
        try {
            TimeShift timeShift = timeShiftTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(timeShift.getId()));
            doctorIdText.setText(String.valueOf(timeShift.getDoctor().getId()));
            medicalIdText.setText(String.valueOf(timeShift.getMedical().getId()));
            startDateTime.setValue(timeShift.getStartDateTime().toLocalDate());
            endDateTime.setValue(timeShift.getEndDateTime().toLocalDate());

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

    private void searchByDoctorId() {
        try {
            String doctorIdStr = searchDoctorIdText.getText();
            if (doctorIdStr == null || doctorIdStr.isEmpty()) {
                resetForm();
                return;
            }
            int doctorId = Integer.parseInt(doctorIdStr);
            showDataOnTable(TimeShiftService.getService().findTimeShiftByDoctorId(doctorId));
            log.info("TimeShift FindByDoctor Id Successfully :");
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "لطفا یک عدد معتبر وارد کنید!", ButtonType.OK);
            alert.show();
            log.error("Invalid DoctorId input: " + e.getMessage());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching Data !!!", ButtonType.OK);
            alert.show();
            log.error("TimeShift Find By DoctorId Failed "+ e.getMessage());
        }
    }

}
