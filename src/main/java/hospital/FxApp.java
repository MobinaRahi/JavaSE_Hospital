package hospital;

import hospital.model.tools.FormLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FxApp extends Application {
    public static void main(String[] args) throws Exception {
        log.info("Starting FxApp");
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FormLoader.getFormLoader().showStage(primaryStage, "/view/LoginView.fxml", "Login");
        //FormLoader.getFormLoader().showStage(primaryStage, "/view/DoctorView.fxml", "doctor");
        //FormLoader.getFormLoader().showStage(primaryStage, "/view/PrescriptionView.fxml", "Prescription");
        //FormLoader.getFormLoader().showStage(primaryStage, "/view/payment.fxml", "Payment");
        //FormLoader.getFormLoader().showStage(primaryStage, "/view/Patient.fxml", "Patient");
        //FormLoader.getFormLoader().showStage(primaryStage, "/view/Medical.fxml", "Medical");
        //FormLoader.getFormLoader().showStage(primaryStage, "/view/CashDesk.fxml", "CashDesk");
        //FormLoader.getFormLoader().showStage(primaryStage, "/view/Drug.fxml", "Drug");
        // FormLoader.getFormLoader().showStage(primaryStage, "/view/DrugStock.fxml", "DrugStock");
    }
}
