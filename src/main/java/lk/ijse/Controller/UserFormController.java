package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.Userdto;
import lk.ijse.bo.custom.impl.UserBOImpl;

import java.sql.SQLException;

public class UserFormController {

    @FXML
    private Button SaveOnAction;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    public void ClearOnAction(ActionEvent event) {
        clear();
    }

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);


    private void clear() {
        txtUserId.clear();
        txtUserName.clear();
        txtEmail.clear();
        txtPassword.clear();
    }

    @FXML
    void DeleteOnAction(ActionEvent event) {
        String id = txtUserId.getText();

        var model = new UserBOImpl();

        try {
            boolean isDelete = model.deleteUser(id);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Delete!").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    @FXML
    void SaveOnAction(ActionEvent event) {
        String UserId = txtUserId.getText();
        String UserName = txtUserName.getText();
        String Email = txtEmail.getId();
        String Password = txtPassword.getText();




        try {
            UserBOImpl userModel = new UserBOImpl();
            boolean b = userBO.saveUser(new Userdto(UserId,UserName,Email,Password ));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "User saved!").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public void updateOnAction(ActionEvent actionEvent) {
        String UserId = txtUserId.getText();
        String UserName = txtUserName.getText();
        String Email = txtEmail.getId();
        String Password = txtPassword.getText();




        var dto = new Userdto(UserId,UserName,Email,Password );

        var model = new UserBOImpl();

        try {
            boolean isSaved = model.updateUser(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Update!").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {

        String code = txtUserId.getText();

        try {
            Userdto dto = userBO.searchUser(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "user not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(Userdto dto) {
        txtUserId.setText(dto.getUserId());
        txtUserName.setText(dto.getName());
        txtPassword.setText(dto.getPassword());
        txtEmail.setText(dto.getEmail());

    }

}
