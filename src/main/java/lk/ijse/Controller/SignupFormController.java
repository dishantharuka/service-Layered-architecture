package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.Userdto;

import java.io.IOException;
import java.util.Objects;
public class SignupFormController {

    @FXML
    private AnchorPane Pane;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void AgreeOnAction(ActionEvent event) {


    }

    @FXML
    void LogInOnAction(ActionEvent event) throws IOException {

        Parent rootNode = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/login_form.fxml")));

        Scene scene = new Scene(rootNode);

        Pane.getChildren().clear();
        Stage primaryStage = (Stage) Pane.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Form");

    }

    @FXML
    void SignUpOnAction(ActionEvent event) throws Exception {
        boolean userCheck = txtEmail.getText().equals(userBO.getEmail(txtEmail.getText()));
        if (!userCheck) {

            Userdto userDTO = new Userdto();
            userDTO.setUserId(txtUserId.getText());
            userDTO.setName(txtName.getText());
            userDTO.setEmail(txtEmail.getText());
            userDTO.setPassword(txtPassword.getText());


            boolean saved = userBO.saveUser(userDTO);
            if (saved) {
                new Alert(Alert.AlertType.CONFIRMATION, "user Saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "user  not saved").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Already exist ").show();
        }
        Clear();
    }

     void Clear() {
        txtName.clear();
        txtUserId.clear();
        txtEmail.clear();
        txtPassword.clear();
    }

}