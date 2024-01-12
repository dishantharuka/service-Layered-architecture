package lk.ijse.Controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.impl.AppoinmentBOImpl;
import lk.ijse.bo.custom.impl.CustomerBOImpl;

import java.sql.SQLException;

public class MainFormController{

                @FXML
                private AnchorPane ChangePane;

                @FXML
                private Label lblTotalCus;

                @FXML
                private Label lblTotalService;

                public void initialize() throws SQLException {
                    setlblCustomerCount();
                    setlblAppointmentCount();
                }

                CustomerBOImpl customerModel = new CustomerBOImpl();
                AppoinmentBOImpl appoinmentModel = new AppoinmentBOImpl();
    private void setlblCustomerCount() throws SQLException {
          String customerCount = customerModel.getCustomerCount();
          lblTotalCus.setText(customerCount);
    }

    private void setlblAppointmentCount() throws SQLException {
        String serviceCount = appoinmentModel.getAppointmentCount();
        lblTotalService.setText(serviceCount);
    }


}



