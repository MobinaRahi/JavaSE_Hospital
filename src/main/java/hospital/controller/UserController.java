package hospital.controller;

import hospital.model.entity.User;
import hospital.model.entity.enums.Role;
import hospital.model.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
@Log4j2
public class UserController implements Initializable {

    @FXML
    private TextField idText,nameText,familyText,userNameText,passwordText,nickNameText,searchNameText,searchFamilyText;

    @FXML
    private DatePicker birthDate,registerDate;

    @FXML
    private ComboBox<Role> roleCombo;

    @FXML
    private RadioButton statusEnableRadio,statusDisableRadio,lockedEnableRadio,lockedDisableRadio;

    @FXML
    private Button saveButton,editButton,deleteButton;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User,Integer> idColumn;

    @FXML
    private TableColumn<User,String> userNameColumn,familyColumn,nameColumn,passwordColumn,nickNameColumn;

    @FXML
    private TableColumn<User, LocalDate>birthDateColumn,registerDateColumn;

    @FXML
    private TableColumn<User,Role>roleColumn;

    @FXML
    private TableColumn<User,Boolean> statusColumn,lockedColumn;
    @Override
    public void initialize (URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }

        saveButton.setOnAction((event) -> {
            try {
                User user =
                        User
                                .builder()
                                .name(nameText.getText())
                                .family(familyText.getText())
                                .birthDate(birthDate.getValue())
                                .role(roleCombo.getSelectionModel().getSelectedItem())
                                .status(statusEnableRadio.isSelected())
                                .username(userNameText.getText())
                                .password(passwordText.getText())
                                .nickname(nickNameText.getText())
                                .locked(lockedEnableRadio.isSelected())
                                .registerDate(registerDate.getValue())
                                .build();
                UserService.getService().save(user);
                log.info("User Saved Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully\n" + user, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("User Save Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "User Save Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });


        editButton.setOnAction((event) -> {
            try {
                User user =
                        User
                                .builder()
                                .id(Integer.parseInt(idText.getText()))
                                .name(nameText.getText())
                                .family(familyText.getText())
                                .birthDate(birthDate.getValue())
                                .role(roleCombo.getSelectionModel().getSelectedItem())
                                .status(statusEnableRadio.isSelected())
                                .username(userNameText.getText())
                                .password(passwordText.getText())
                                .nickname(nickNameText.getText())
                                .locked(lockedEnableRadio.isSelected())
                                .registerDate(registerDate.getValue())
                                .build();
                UserService.getService().edit(user);
                log.info("User Edited Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited Successfully\n" + user, ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("User Edit Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "User Edit Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });


        deleteButton.setOnAction((event) -> {
            try {
                UserService.getService().delete(Integer.parseInt(idText.getText()));
                log.info("User Deleted Successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully\n" + idText.getText(), ButtonType.OK);
                alert.show();
                resetForm();
            } catch (Exception e) {
                log.error("User Delete Failed " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, "User Delete Failed " + e.getMessage(), ButtonType.OK);
                alert.show();
            }
        });

        searchNameText.setOnKeyReleased((event) -> searchByNameAndFamily());
        searchFamilyText.setOnKeyReleased((event) -> searchByNameAndFamily());


        userTable.setOnMouseReleased((event) -> selectFromTable());

        userTable.setOnKeyReleased((event) -> selectFromTable());
    }

    private void resetForm() throws Exception {
        idText.clear();
        nameText.clear();
        familyText.clear();
        userNameText.clear();
        passwordText.clear();
        nickNameText.clear();
        birthDate.setValue(LocalDate.now());
        registerDate.setValue(LocalDate.now());

        for (Role role : Role.values()) {
            roleCombo.getItems().add(role);
        }
        roleCombo.getSelectionModel().select(0);

        statusEnableRadio.setSelected(true);
        lockedEnableRadio.setSelected(true);


        showDateOnTable(UserService.getService().findAll());
    }

    private void showDateOnTable(List<User> userList) {
        ObservableList<User> observableList = FXCollections.observableList(userList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        familyColumn.setCellValueFactory(new PropertyValueFactory<>("family"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        nickNameColumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        lockedColumn.setCellValueFactory(new PropertyValueFactory<>("locked"));
        registerDateColumn.setCellValueFactory(new PropertyValueFactory<>("registerDate"));

        userTable.setItems(observableList);
    }

    public void selectFromTable() {
        try {
            User user = userTable.getSelectionModel().getSelectedItem();
            idText.setText(String.valueOf(user.getId()));
            nameText.setText(user.getName());
            familyText.setText(user.getFamily());
            birthDate.setValue(user.getBirthDate());
            roleCombo.getSelectionModel().select(user.getRole());
            statusEnableRadio.setSelected(user.getStatus());
            statusDisableRadio.setSelected(!user.getStatus());
            userNameText.setText(user.getUsername());
            passwordText.setText(user.getPassword());
            nickNameText.setText(user.getNickname());
            lockedEnableRadio.setSelected(user.getLocked());
            lockedDisableRadio.setSelected(!user.getLocked());
            registerDate.setValue(user.getRegisterDate());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Loading Data !!!", ButtonType.OK);
            alert.show();
        }
    }

    public void searchByNameAndFamily() {
        try {
            showDateOnTable(UserService.getService().findByNameAndFamily(searchNameText.getText(), searchFamilyText.getText()));
            log.info("Users FindByNameAndFamily :" + searchNameText.getText() + " " + searchFamilyText.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Searching Data !!!", ButtonType.OK);
            alert.show();
            log.error("User FindNameFamily " + searchNameText.getText() + " " + searchFamilyText.getText() + " Failed " + e.getMessage());
        }
    }

}
