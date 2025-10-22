package hospital.controller;

import hospital.model.entity.Drug;
import hospital.model.entity.Prescription;
import hospital.model.service.DrugService;
import hospital.model.service.PrescriptionService;
import hospital.model.tools.FormLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


@Log4j2
@Setter
@Getter
public class AddDrugController implements Initializable {

    private int prescriptionId;
    @FXML
    private TextField searchNameText;

    @FXML
    private Button saveButton;

    @FXML
    private TableView<Drug> drugTable;

    @FXML
    private TableColumn<Drug, Integer> idColumn, quantityColumn;

    @FXML
    private TableColumn<Drug, String> nameColumn;

    @FXML
    private TableColumn<Drug, Double> priceColumn;

    @Setter
    private Prescription prescriptionToken;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }


        searchNameText.setOnKeyReleased((event) -> searchByName());

        drugTable.setOnKeyReleased((event) -> selectFromTable());
        drugTable.setOnMouseReleased((event) -> selectFromTable());
    }

    private void resetForm() throws Exception {

        showDateOnTable(DrugService.getService().findAll());
    }

    private void showDateOnTable(List<Drug> drugList) {
        ObservableList<Drug> observableList = FXCollections.observableList(drugList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        drugTable.setItems(observableList);
    }

    private void selectFromTable() {
        try {
            Drug drug = drugTable.getSelectionModel().getSelectedItem();
            saveButton.setOnAction(event -> {
                try {
                    PrescriptionService.getService().addDrugToPrescription(prescriptionId,drug.getId());
                    Stage stage = new Stage();
                    FormLoader.getFormLoader().showStage(stage, "/view/PrescriptionView.fxml.", "Prescription");
                    System.out.println("save prescription Successfully"+drug.getName());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

    public void searchByName() {
        try {
            showDateOnTable(DrugService.getService().findByName(searchNameText.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching Data !!!", ButtonType.OK);
            alert.show();
            log.error("Error FindName" + searchNameText.getText() + " " + searchNameText.getText() + " Failed " + e.getMessage());
        }
    }
}

