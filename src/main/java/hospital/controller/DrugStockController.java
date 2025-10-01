package hospital.controller;

import hospital.model.entity.DrugStock;
import hospital.model.service.DrugService;
import hospital.model.service.DrugStockService;
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
public class DrugStockController implements Initializable {
    @FXML
    private TextField idText, drugIdText, drugCountText;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<DrugStock> drugStockTable;

    @FXML
    private TableColumn<DrugStock, Integer> idColumn, drugCountColumn;

    @FXML
    private TableColumn<DrugStock, String> drugIdColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            resetForm();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        saveButton.setOnAction(event -> {
            try {
                DrugStock drugStock =
                        DrugStock
                                .builder()
                                .drug(DrugService.getService().findById(Integer.parseInt(drugIdText.getText())))
                                .drugCount(Integer.parseInt(drugCountText.getText()))
                                .build();

                DrugStockService.getService().save(drugStock);
                log.info("Drug Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + drugStock, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Drug Save Failed" + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "DrugStock Save Failed" + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction(event -> {
            try {
                DrugStock drugStock =
                        DrugStock
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .drug(DrugService.getService().findById(Integer.parseInt(drugIdText.getText())))
                                .drugCount(Integer.parseInt(drugCountText.getText()))
                                .build();

                DrugStockService.getService().edit(drugStock);
                log.info("DrugStock  Edit Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + drugStock, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "DrugStock Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction(event -> {
            try {
                DrugStockService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("Drug stock Delete Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("DrugStock Delete Failed" + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "DrugStock Delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        drugStockTable.setOnKeyReleased((event) -> selectFromTable());
        drugStockTable.setOnMouseReleased((event) -> selectFromTable());

    }

    private void resetForm() throws Exception {
        idText.clear();
        drugIdText.clear();
        drugCountText.clear();

        showDateOnTable(DrugStockService.getService().findAll());
    }

    private void showDateOnTable(List<DrugStock> drugStockList) {
        ObservableList<DrugStock> drugStockObservableList = FXCollections.observableArrayList(drugStockList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        drugIdColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDrug().getName())
        );
        drugCountColumn.setCellValueFactory(new PropertyValueFactory<>("drugCount"));
        drugStockTable.setItems(drugStockObservableList);

    }

    private void selectFromTable() {
        try {
            DrugStock drugStock = drugStockTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(drugStock.getId()));
            drugIdText.setText(String.valueOf(drugStock.getDrug().getId()));
            drugCountText.setText(String.valueOf(drugStock.getDrugCount()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }
}
