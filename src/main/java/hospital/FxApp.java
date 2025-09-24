package hospital;

import hospital.model.tools.FormLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FxApp extends Application {
    public static void main(String[] args) throws Exception{
        log.info("Starting FxApp");
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FormLoader.getFormLoader().showStage(primaryStage, "/view/doctor.fxml", "doctor");
    }
}
