package hospital.controller;

import hospital.model.entity.Drug;
import hospital.model.service.DrugService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.*;



@Log4j2
public class DrugController implements Initializable {
    @FXML
    private TextField idText, nameText, priceText, quantityText, searchNameText;

    @FXML
    private Button saveButton,editButton,deleteButton;

    @FXML
    private TableView<Drug> drugTable;

    @FXML
    private TableColumn<Drug,Integer> idColumn, quantityColumn;

    @FXML
    private TableColumn<Drug,String> nameColumn;

    @FXML
    private TableColumn<Drug,Double> priceColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            resetForm();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }

        saveButton.setOnAction(event -> {
            try {
                Drug drug =
                        Drug
                                .builder()
                                .name(nameText.getText())
                                .price(Double.parseDouble(priceText.getText()))
                                .quantity(Integer.parseInt(quantityText.getText()))
                                .build();
                DrugService.getService().save(drug);
                log.info("Drug Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + drug, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Drug save failed" + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Drug Save Failed" + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction(event -> {
            try {
                Drug drug =
                        Drug
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .name(nameText.getText())
                                .price(Double.parseDouble(priceText.getText()))
                                .quantity(Integer.parseInt(quantityText.getText()))
                                .build();
                DrugService.getService().edit(drug);
                log.info("Drug Edit Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + drug, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Drug Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction(event -> {
                try {
                    DrugService.getService().delete(Integer.parseInt(idText.getText()));
                    log.info("Drug delete Successfully");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + idText.getText(), ButtonType.OK);
                    alert.show();
                    resetForm();
                } catch (Exception e) {
                    log.error("Drug  delete failed" + e.getMessage());
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Drug Delete Failed " + e.getMessage(), ButtonType.OK);
                    alert.show();
                }
        });
        searchNameText.setOnKeyReleased((event) -> searchByName());

        drugTable.setOnKeyReleased((event) -> selectFromTable());
        drugTable.setOnMouseReleased((event) -> selectFromTable());
    }

    private void resetForm() throws Exception {
        idText.clear();
        nameText.clear();
        priceText.clear();
        quantityText.clear();

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
            idText.setText(String.valueOf(drug.getId()));
            nameText.setText(drug.getName());
            priceText.setText(String.valueOf(drug.getPrice()));
            quantityText.setText(String.valueOf(drug.getQuantity()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

    public void searchByName () {
        try {
            showDateOnTable(DrugService.getService().findByName(searchNameText.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching Data !!!", ButtonType.OK);
            alert.show();
            log.error("Error FindName" + searchNameText.getText() + " " + searchNameText.getText() + " Failed " + e.getMessage());
        }
    }
}
