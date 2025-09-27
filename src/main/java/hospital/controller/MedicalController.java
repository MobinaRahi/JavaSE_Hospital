package hospital.controller;

import hospital.model.entity.Medical;
import hospital.model.service.DoctorService;
import hospital.model.service.MedicalService;
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
public class MedicalController implements Initializable {

    @FXML
    private TextField idText, titleText, descriptionText, doctorIdText, durationText, priceText, searchDoctorIdText;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<Medical> medicalTable;

    @FXML
    private TableColumn<Medical, String> titleColumn, descriptionColumn;

    @FXML
    private TableColumn<Medical, Integer> idColumn, doctorIdColumn, durationColumn;

    @FXML
    private TableColumn<Medical, Float> priceColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            restForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data", ButtonType.OK);
            alert.show();
        }

        saveButton.setOnAction(event -> {
            try {
                Medical medical =
                        Medical
                                .builder()
                                .title(titleText.getText())
                                .description(descriptionText.getText())
                                .doctor(DoctorService.getService().findById(Integer.parseInt(doctorIdText.getText())))
                                .duration(Integer.parseInt(durationText.getText()))
                                .price(Float.parseFloat(priceText.getText()))
                                .build();
                MedicalService.getService().save(medical);
                log.info("Medical Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + medical, ButtonType.OK);
                alert.show();
                restForm();
            } catch (Exception e) {
                log.error("medical Save Failed" + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Medical Save Failed" + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction(event -> {
            try {
                Medical medical =
                        Medical
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .title(titleText.getText())
                                .description(descriptionText.getText())
                                .doctor(DoctorService.getService().findById(Integer.parseInt(doctorIdText.getText())))
                                .duration(Integer.parseInt(durationText.getText()))
                                .price(Float.parseFloat(priceText.getText()))
                                .build();
                MedicalService.getService().edit(medical);
                log.info("Medical Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + medical, ButtonType.OK);
                alert.show();
                restForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Medical Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction((event) -> {
            try {
                MedicalService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("Medical Deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                restForm();
            } catch (Exception e) {
                log.error("Medical Delete Failed" + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Medical Delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        medicalTable.setOnMouseReleased((event) -> selectFromTable());
        medicalTable.setOnKeyReleased((event) -> selectFromTable());

        searchDoctorIdText.setOnKeyReleased((event) -> searchByDoctorId());
    }

    private void restForm() throws Exception {
        idText.clear();
        titleText.clear();
        descriptionText.clear();
        doctorIdText.clear();
        durationText.clear();
        priceText.clear();
        searchDoctorIdText.clear();

       showDateOnTable(MedicalService.getService().findAll());
    }

    private void showDateOnTable(List<Medical> medicalList) {
        ObservableList<Medical> medicalObservableList = FXCollections.observableArrayList(medicalList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        doctorIdColumn.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        medicalTable.setItems(medicalObservableList);
    }


    public void selectFromTable() {
        try {
            Medical medical = medicalTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(medical.getId()));
            titleText.setText(medical.getTitle());
            descriptionText.setText(medical.getDescription());
            doctorIdText.setText(String.valueOf(medical.getDoctor().getId()));
            durationText.setText(String.valueOf(medical.getDuration()));
            priceText.setText(String.valueOf(medical.getPrice()));
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

    public void searchByDoctorId() {
        try {
            showDateOnTable(MedicalService.getService().findByDoctorId(Integer.parseInt(searchDoctorIdText.getText())));
            log.info("medical findByDoctorId" + searchDoctorIdText.getText() + "Successfully");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            log.error("medical findByDoctorId" + searchDoctorIdText.getText() + "error" + e.getMessage());
            alert.show();
        }
    }
}
