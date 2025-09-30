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
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



@Log4j2

public class AddDrugController implements Initializable {

        @FXML
        private TextField searchNameText;

        @FXML
        private Button saveButton;

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

                });

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

