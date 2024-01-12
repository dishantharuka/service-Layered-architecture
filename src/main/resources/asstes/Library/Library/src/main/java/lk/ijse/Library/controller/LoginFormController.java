package lk.ijse.Library.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private AnchorPane Pane;

    @FXML
    void CreateNewOneOnAction(ActionEvent event) throws IOException {
        navigate.navigate(Pane,"signup_form.fxml","signup");
    }

    @FXML
    void LoginOnAction(ActionEvent event) throws IOException {
        navigate.navigate(Pane,"dashbord_form.fxml","dashbord");
    }

}
