package lk.ijse.bo.custom.impl;


import lk.ijse.bo.custom.OrderDetailBO;
import lk.ijse.dao.Custom.Impl.OrderDetailDAOImpl;
import lk.ijse.dao.Custom.OrderDetailDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailBOImpl implements OrderDetailBO {

    OrderDetailDAO orderDetailDAO=new OrderDetailDAOImpl();
    public boolean saveOrderDetail(String orderId, List<CartTm> tmList) throws SQLException {
        for (CartTm cartTm : tmList) {
            if(!saveOrder(orderId, cartTm)) {
                return false;
            }
        }
        return true;
    }
    public boolean saveOrder(String orderId, CartTm cartTm) throws SQLException {

        return SQLUtil.execute("INSERT INTO orderDetails VALUES(?, ?, ?)",orderId,cartTm.getCode(),cartTm.getQty());
    }
}
