package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderBO extends SuperBO {


        boolean saveOrder(String orderId, String cusId, LocalDate date) throws SQLException;
        String generateNextOrderId() throws SQLException ;
        String splitOrderId(String currentOrderId) ;
}
