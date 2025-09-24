package hospital.controller;

import hospital.model.entity.Payment;
import hospital.model.entity.enums.PayFor;
import hospital.model.entity.enums.PayType;
import hospital.model.service.PaymentService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.extern.log4j.Log4j2;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
@Log4j2
public class PaymentController implements Initializable{
    @FXML
    private TextField idText,priceText;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private ComboBox<PayType> payTypeCombo;
    @FXML
    private ComboBox<PayFor> payForCombo;
    @FXML
    private Button saveButton,editButton,deleteButton;
    @FXML
    private TableView<Payment> paymentTable;
    @FXML
    private TableColumn<Payment,Integer> idColumn,priceColumn;
    @FXML
    private TableColumn<Payment, LocalDateTime>dateColumn;
    @FXML
    private TableColumn<Payment,PayType> payTypeColumn;
    @FXML
    private TableColumn<Payment,PayFor> payForColumn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private void resetForm(){
        idText.clear();
        for( PayType payType:PayType.values()){
            payTypeCombo.getItems().add(payType);
        }
        payTypeCombo.getSelectionModel().select(0);
        DatePicker.setValue(LocalDate.now());

        for( PayFor payFor :PayFor.values()){
            payForCombo.getItems().add(payFor);
        }
        priceText.clear();
      //  showDateOnTable(PaymentService.getService().findAll());
    }
    private void showDateOnTable(List<Payment> paymentList){}
}
