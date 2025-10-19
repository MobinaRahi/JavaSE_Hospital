package hospital.controller;

import hospital.model.entity.User;
import hospital.model.entity.enums.Role;
import hospital.model.service.UserService;
import hospital.model.tools.FormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Log4j2
public class SignInController implements Initializable {
    @FXML
    private TextField usernameText, nameText, familyText, nickNameText, passwordText;

    @FXML
    private ComboBox<Role> roleCombo;

    @FXML
    private DatePicker birthDate;

    @FXML
    private Button SignInBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }

        SignInBtn.setOnAction(e -> {
            try {
                User user = User
                        .builder()
                        .name(nameText.getText())
                        .family(familyText.getText())
                        .birthDate(birthDate.getValue())
                        .role(roleCombo.getSelectionModel().getSelectedItem())
                        .username(usernameText.getText())
                        .password(passwordText.getText())
                        .status(true)
                        .locked(true)
                        .nickname(nickNameText.getText())
                        .registerDate(LocalDate.now())
                        .build();

                UserService.getService().save(user);
                move();
                resetForm();
            } catch (Exception ex) {
                log.error("Error while saving user", ex);
                Alert alert = new Alert(
                        Alert.AlertType.ERROR,
                        "Error while saving user:\n" + ex.getMessage(),
                        ButtonType.OK
                );
                alert.show();
            }

        });
    }

    public void resetForm() throws Exception {
        usernameText.clear();
        familyText.clear();
        nickNameText.clear();
        passwordText.clear();

        for (Role role : Role.values()) {
            roleCombo.getItems().add(role);
        }

        roleCombo.getSelectionModel().select(0);
    }

    public void move() {
        try {
            Stage stage = new Stage();
            FormLoader.getFormLoader().showStage(stage, "/view/FirstView.fxml.", "Hello");
            SignInBtn.getScene().getWindow().hide();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
        }
    }
}
