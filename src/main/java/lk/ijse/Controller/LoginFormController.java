package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Launcher;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;



public class LoginFormController {

        @FXML
        private CheckBox RememberMeOnAction;

        @FXML
        private Hyperlink hyperSignUpOnAction;

        @FXML
        private TextField txtUserName;

        @FXML
        private TextField txtPassword;



        @FXML
        private AnchorPane Pane;
    private Navigate navigat;
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
        void LoginOnAction(ActionEvent event) throws IOException, SQLException {

            String password = txtPassword.getText();
            String userName = txtUserName.getText();



            boolean credentials = userBO.isExistUser(userName,password);

            if (credentials){
                Launcher.stage.close();
                Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/Dashboard_form.fxml"))));
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setMaxHeight(768);
                stage.setMaxWidth(1366);
                stage.show();
            }else {
                new Alert(Alert.AlertType.ERROR,"User Name & Password Wrong.. Try Again..!").show();
                txtUserName.clear();
                txtPassword.clear();
            }

        }



        @FXML
        void SignupOnAction(ActionEvent event) throws IOException {
                Launcher.stage.close();
                Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/Signup_form.fxml"))));
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setMaxHeight(768);
                stage.setMaxWidth(1366);
                stage.show();
        }

    }

