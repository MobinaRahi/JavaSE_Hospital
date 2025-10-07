package hospital.controller;

import hospital.model.entity.Bank;
import hospital.model.entity.CashDesk;
import hospital.model.entity.enums.PayType;
import hospital.model.service.BankService;
import hospital.model.service.CashDeskService;
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
public class CashDeskController implements Initializable {

    @FXML
    private TextField idText, bankText;

    @FXML
    private ComboBox<PayType> payTypeCombo;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<CashDesk> cashDeskTable;

    @FXML
    private TableColumn<CashDesk, Integer> idColumn;

    @FXML
    private TableColumn<CashDesk, String> bankColumn;

    @FXML
    private TableColumn<CashDesk, PayType> payTypeColumn;

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
                List<Bank> banks = BankService.getService().findAll();

                int bankId = 1; // Default
                for (Bank b : banks) {
                    if (b.getTitle().equalsIgnoreCase(bankText.getText())) {
                        bankId = b.getId();
                    }
                }

                Bank bank = BankService.getService().findById(bankId);
                CashDesk cashDesk = CashDesk
                        .builder()
                        .bank(bank)
                        .payType(payTypeCombo.getSelectionModel().getSelectedItem())
                        .build();
                CashDeskService.getService().save(cashDesk);
                log.info("CashDesk Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("CashDesk Save Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Failed\n" + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction(event -> {
            try {

                List<Bank> banks = BankService.getService().findAll();

                int bankId = 1; // Default
                for (Bank b : banks) {
                    if (b.getTitle().equalsIgnoreCase(bankText.getText())) {
                        bankId = b.getId();
                    }
                }
                Bank bank = BankService.getService().findById(bankId); // TODO: change this
                CashDesk cashDesk = CashDesk
                        .builder()
                        .id(Integer.parseInt(idText.getText()))
                        .bank(bank)
                        .payType(payTypeCombo.getSelectionModel().getSelectedItem())
                        .build();
                CashDeskService.getService().edit(cashDesk);
                log.info("CashDesk Edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("CashDesk Edit Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Edit Failed\n" + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction(event -> {
            try {
                CashDeskService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("CashDesk Deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("CashDesk Delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Delete Failed\n" + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        cashDeskTable.setOnMouseReleased(event -> selectFromTable());
        cashDeskTable.setOnKeyReleased(event -> selectFromTable());
    }

    private void resetForm() throws Exception {
        idText.clear();
        bankText.clear();
        payTypeCombo.getItems().clear();

        payTypeCombo.setItems(FXCollections.observableArrayList(PayType.values()));
        payTypeCombo.getSelectionModel().selectFirst();

        showDataOnTable(CashDeskService.getService().findAll());
    }

    private void showDataOnTable(List<CashDesk> cashDeskList) {
        ObservableList<CashDesk> observableList = FXCollections.observableArrayList(cashDeskList);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bankColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getBank().getTitle())
        );
        payTypeColumn.setCellValueFactory(new PropertyValueFactory<>("payType"));
        cashDeskTable.setItems(observableList);
    }

    private void selectFromTable() {
        try {
            CashDesk cashDesk = cashDeskTable.getSelectionModel().getSelectedItem();
            if (cashDesk == null) return;
            idText.setText(String.valueOf(cashDesk.getId()));
            bankText.setText(String.valueOf(cashDesk.getBank().getTitle()));
            payTypeCombo.getSelectionModel().select(cashDesk.getPayType());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }
}
