package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailBO extends SuperBO {

    boolean saveOrderDetail(String orderId, List<CartTm> tmList) throws SQLException;

    boolean saveOrder(String orderId, CartTm cartTm) throws SQLException;

}
