package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.bo.custom.impl.CustomerBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;

import javax.swing.text.TableView;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerFormController {

    public javafx.scene.control.TableView tblCustomer2;
    public Label lblCustomerID;
    public ComboBox cmbUserId;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCustomerID;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private TableView tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtUserId;

    UserBOImpl userModel = new UserBOImpl();

    CustomerBOImpl customerModel = new CustomerBOImpl();

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize(){

        tblCustomer2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            CustomerDto Dto = (CustomerDto) newValue;
            if (newValue != null) {
                lblCustomerID.setText(Dto.getId());
                cmbUserId.setValue(Dto.getUserId());
                txtAddress.setText(Dto.getAddress());
                txtEmail.setText(Dto.getEmail());
                txtName.setText(Dto.getName());
                txtPhoneNumber.setText(Dto.getTel());

            }
        });

        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));


        loadData();
        generateNextID();
        setUserIds();
    }

    private void setUserIds() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try{
            String[] userId = userModel.loadAllUsers();

            for (String userIds : userId){
                observableList.add(userIds);
            }
            cmbUserId.setItems(observableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextID() {
        try {
            String ID = customerModel.generateNextID();
            lblCustomerID.setText(ID);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private void loadData() {


        try {
            ArrayList<CustomerDto>    all = customerBO.getAll();
            tblCustomer2.getItems().setAll(FXCollections.observableArrayList(all));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void ClearOnAction(ActionEvent event) {
        clear();
    }


    private void clear() {
        txtName.clear();
        txtPhoneNumber.clear();
        txtEmail.clear();
        txtAddress.clear();

    }

    @FXML
    void DeleteOnAction(ActionEvent event) {
        String id = lblCustomerID.getText();

        var model = new CustomerBOImpl();

        try {
            boolean isDelete = model.deleteCustomer(id);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer Delete!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    @FXML
    void SaveOnAction(ActionEvent event) {
        String id = lblCustomerID.getText();
        String name = txtName.getText();
        String tel = txtPhoneNumber.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String userId = (String) cmbUserId.getValue();


        try {
            CustomerBOImpl customerModel = new CustomerBOImpl();
            boolean b = customerModel.saveCustomer(new CustomerDto(id, name, tel, email, address, userId));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) {
        String id = lblCustomerID.getText();
        String name = txtName.getText();
        String tel = txtPhoneNumber.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String userId = (String) cmbUserId.getValue();



        var dto = new CustomerDto(id, name, tel, email, address,userId);

        var model = new CustomerBOImpl();

        try {
            boolean isSaved = model.updateCustomer(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Update!").show();
                clear();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void searchOnAction(ActionEvent actionEvent) {

        String code = txtCustomerId.getText();

        try {
            CustomerDto dto = customerBO.searchCustomer(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Customer not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(CustomerDto dto) {
        txtCustomerId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtPhoneNumber.setText(dto.getTel());
        txtEmail.setText(dto.getEmail());
        txtAddress.setText(dto.getAddress());
        txtUserId.setText(dto.getUserId());
    }

    private boolean validateOrders() {

        String CustomerID = txtCustomerId.getText();
        Pattern compile = Pattern.compile("[O][0-9]{3,}");
        Matcher matcher = compile.matcher(CustomerID);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Orders ID").show();
        }
        return false;
    }

}