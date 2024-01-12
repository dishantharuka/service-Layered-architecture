package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.dto.CustomerDto;
import lk.ijse.bo.custom.impl.CustomerBOImpl;
import lk.ijse.bo.orderModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    public Label lblNetTotal;
    public TextField txtNetTotal;
    public TextField txtQtyOnHand;
    public TextField txtQty;
    public TextField txtUnitPrice;
    public TextField txtDescription;
    public ComboBox cmbCustomerId;
    public ComboBox cmbCode;
    public Label lbQtyOnHand;
    public Label lblUnitPrice;
    public Label lblQty;
    public TextField txtCustomerName;
    public TextField txtOrderDate;
    public Label lblCode;
    public Label lblDescription;
    public Label lblCustomerName;
    public Label lblCustomerId;
    public Label lblOrderDate;
    public Label lblOrderId;
    public TextField txtOrderId;
    public TableView tblOrder;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TableColumn colAction;

    public void btnAddToCart(ActionEvent actionEvent) {
        // sout txtfeild data print
    }

    public void btnPlaceOrder(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValue();
    }

    private void setValue() {
        CustomerBOImpl customerModel = new CustomerBOImpl();
        ArrayList<String> allId = customerModel.getAllId();

        ObservableList<String> objects = FXCollections.observableArrayList();


        for (int i = 0; i < objects.size(); i++) {
            System.out.println(objects.get(i));
        }

        cmbCustomerId.setItems(objects);
    }

    public void cmbCOdeOnAction(ActionEvent actionEvent) {
    }

    public void cmbCustomerOnAction(ActionEvent actionEvent) {
        CustomerBOImpl customerModel = new CustomerBOImpl();
        CustomerDto customerDto = customerModel.searchCustomer(String.valueOf(cmbCustomerId.getValue()));
        txtCustomerName.setText(customerDto.getName());
    }

}
