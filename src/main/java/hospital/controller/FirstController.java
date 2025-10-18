package hospital.controller;

import hospital.model.tools.FormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FirstController implements Initializable {
    @FXML
    private Button userButton,doctorButton,timeShiftButton,prescriptionButton,paymentButton
            ,patientButton,medicalButton,cashDeskButton,drugStockButton,visitButton,employeeButton,drugButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userButton.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FormLoader.getFormLoader().showStage(stage, "/view/UserView.fxml.", "User Information");
                userButton.getScene().getWindow().hide();
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
            }
        });
        doctorButton.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FormLoader.getFormLoader().showStage(stage, "/view/DoctorView.fxml.", "Doctor Information");
                doctorButton.getScene().getWindow().hide();
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
            }
        });
        timeShiftButton.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FormLoader.getFormLoader().showStage(stage, "/view/timeShiftView.fxml.", "timeShift Information");
                timeShiftButton.getScene().getWindow().hide();
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
            }
        });
        prescriptionButton.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FormLoader.getFormLoader().showStage(stage, "/view/prescriptionView.fxml.", "prescription Information");
                prescriptionButton.getScene().getWindow().hide();
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
            }
        });
        paymentButton.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FormLoader.getFormLoader().showStage(stage, "/view/paymentView.fxml.", "payment Information");
                paymentButton.getScene().getWindow().hide();
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
            }
        });
        patientButton.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FormLoader.getFormLoader().showStage(stage, "/view/patientView.fxml.", "patient Information");
                patientButton.getScene().getWindow().hide();
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
            }
        });
        medicalButton.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FormLoader.getFormLoader().showStage(stage, "/view/medicalView.fxml.", "medical Information");
                medicalButton.getScene().getWindow().hide();
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
            }
        });
        cashDeskButton.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FormLoader.getFormLoader().showStage(stage, "/view/cashDeskView.fxml.", "cashDesk Information");
                cashDeskButton.getScene().getWindow().hide();
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
            }
        });
        drugStockButton.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FormLoader.getFormLoader().showStage(stage, "/view/drugStockView.fxml.", "drugStock Information");
                drugStockButton.getScene().getWindow().hide();
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
            }
        });
        visitButton.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FormLoader.getFormLoader().showStage(stage, "/view/visitView.fxml.", "visit Information");
                visitButton.getScene().getWindow().hide();
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
            }
        });
        employeeButton.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FormLoader.getFormLoader().showStage(stage, "/view/employeeView.fxml.", "employee Information");
                employeeButton.getScene().getWindow().hide();
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
            }
        });
        drugButton.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FormLoader.getFormLoader().showStage(stage, "/view/drugView.fxml.", "drug Information");
                drugButton.getScene().getWindow().hide();
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
            }
        });

    }
}
