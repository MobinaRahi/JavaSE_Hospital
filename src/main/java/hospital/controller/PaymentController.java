package hospital.controller;

import hospital.model.entity.Payment;
import hospital.model.entity.enums.PayFor;
import hospital.model.entity.enums.PayType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.extern.log4j.Log4j;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
@Log4j
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
}
