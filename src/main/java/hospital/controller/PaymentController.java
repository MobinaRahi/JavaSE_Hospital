package hospital.controller;

import hospital.model.entity.Payment;
import hospital.model.entity.enums.PayFor;
import hospital.model.entity.enums.PayType;
import hospital.model.service.PaymentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class PaymentController implements Initializable {
    @FXML
    private TextField idText, priceText;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private ComboBox<PayType> payTypeCombo;
    @FXML
    private ComboBox<PayFor> payForCombo;
    @FXML
    private Button saveButton, editButton, deleteButton;
    @FXML
    private TableView<Payment> paymentTable;
    @FXML
    private TableColumn<Payment, Integer> idColumn;
    @FXML
    private TableColumn<Payment,Float> priceColumn;
    @FXML
    private TableColumn<Payment, LocalDateTime> dateColumn;
    @FXML
    private TableColumn<Payment, PayType> payTypeColumn;
    @FXML
    private TableColumn<Payment, PayFor> payForColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data", ButtonType.OK);
            alert.show();
        }
        saveButton.setOnAction(event -> {
            try {
                Payment payment =
                        Payment
                                .builder()
                                .payType(payTypeCombo.getSelectionModel().getSelectedItem())
                                .payDateTime(DatePicker.getValue().atTime(LocalTime.now()))
                                .price(Float.parseFloat(priceText.getText()))
                                .payFor(payForCombo.getSelectionModel().getSelectedItem())
                                .build();
                PaymentService.getService().save(payment);
                log.info("payment Saved successFully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + payment, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("payment save Failed" + e);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Payment Save Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        editButton.setOnAction(event -> {
            try {
                Payment payment =
                        Payment
                                .builder()
                                .payType(payTypeCombo.getSelectionModel().getSelectedItem())
                                .payDateTime(DatePicker.getValue().atTime(LocalTime.now()))
                                .price(Float.parseFloat(priceText.getText()))
                                .payFor(payForCombo.getSelectionModel().getSelectedItem())
                                .build();
                PaymentService.getService().edit(payment);
                log.info("payment edited successFully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + payment, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("payment edited Failed" + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Payment Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        deleteButton.setOnAction((event) -> {
            try {
                PaymentService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("Payment Delete Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("Payment Delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Payment Delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });
        paymentTable.setOnMouseReleased((event) -> selectFromTable());
        paymentTable.setOnKeyReleased((event) -> selectFromTable());


    }

    private void resetForm() throws Exception {
        idText.clear();
        priceText.clear();
       for (PayType payType : PayType.values()) {
           payTypeCombo.getItems().add(payType);
       }
        payTypeCombo.getSelectionModel().select(0);
        DatePicker.setValue(LocalDate.now());

        for (PayFor payFor : PayFor.values()) {
            payForCombo.getItems().add(payFor);
        }
        payForCombo.getSelectionModel().select(0);
        showDataOnTable(PaymentService.getService().findAll());
    }

    private void showDataOnTable(List<Payment> paymentList) {
        ObservableList<Payment> observableList = FXCollections.observableList(paymentList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        payTypeColumn.setCellValueFactory(new PropertyValueFactory<>("payType"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("payDateTime"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        payForColumn.setCellValueFactory(new PropertyValueFactory<>("payFor"));

        paymentTable.setItems(observableList);
    }


    private void selectFromTable() {
        try {
            Payment payment = paymentTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(payment.getId()));
            payTypeCombo.getSelectionModel().select(payment.getPayType());
            DatePicker.setValue(payment.getPayDateTime().toLocalDate());
            priceText.setText(String.valueOf(payment.getPrice()));
            payForCombo.getSelectionModel().select(payment.getPayFor());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }
}


