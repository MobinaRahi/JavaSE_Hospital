package hospital.controller;

import hospital.model.entity.Bank;



import hospital.model.service.BankService;
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
public class BankController implements Initializable {
    @FXML
    private TextField bankIdText, titleText;
    @FXML
    private Button saveButton, editButton, deleteButton;
    @FXML
    private TableView<Bank> bankTable;
    @FXML
    private TableColumn<Bank, Integer> bankIdColumn;
    @FXML
    private TableColumn<Bank, String> titleColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
        saveButton.setOnAction((event) -> {
            try {
                Bank bank =
                        Bank
                                .builder()
                                .id(Integer.parseInt(bankIdText.getText()))
                                .title(titleText.getText())
                                .build();
                BankService.getService().save(bank);
                log.info("bank Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + bank, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Bank Save Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Bank Save Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        editButton.setOnAction((event) -> {
            try {
                Bank bank =
                        Bank
                                .builder()
                                .id(Integer.parseInt(bankIdText.getText()))
                                .title(titleText.getText())
                                .build();
                BankService.getService().save(bank);
                log.info("bank Edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + bank, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Bank edited Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Bank Edited Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        deleteButton.setOnAction((event) -> {
            try {
                BankService.getService().delete(Integer.parseInt(bankIdText.getText()));
                log.info("bank Deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + bankIdText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Bank Delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Bank Delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        bankTable.setOnMouseReleased((event) -> selectFromTable());

        bankTable.setOnKeyReleased((event) -> selectFromTable());


    }

    private void resetForm() throws Exception {
        bankIdText.clear();
        titleText.clear();
        showDateOnTable(BankService.getService().findAll());

    }

    private void showDateOnTable(List<Bank> bankList) {
        ObservableList<Bank> observableList = FXCollections.observableList(bankList);

        bankIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        bankTable.setItems(observableList);
    }
    public void selectFromTable() {
        try {
            Bank bank = bankTable.getSelectionModel().getSelectedItem();
            bankIdText.setText(String.valueOf(bank.getId()));
            titleText.setText(bank.getTitle());

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

}