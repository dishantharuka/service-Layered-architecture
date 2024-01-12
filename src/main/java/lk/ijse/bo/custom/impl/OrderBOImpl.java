package lk.ijse.bo.custom.impl;


import lk.ijse.bo.custom.OrderBO;
import lk.ijse.dao.Custom.Impl.OrderDAOImpl;
import lk.ijse.dao.Custom.OrderDAO;
import lk.ijse.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderBOImpl implements OrderBO{

    OrderDAO orderDAO=  new OrderDAOImpl();

    public boolean saveOrder(String orderId, String cusId, LocalDate date) throws SQLException {

        return orderDAO.save(orderId,cusId,date);
    }

    public String generateNextOrderId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT OrderId FROM orders ORDER BY OrderId DESC LIMIT 1");

        String currentOrderId = null;

        if (resultSet.next()) {
            currentOrderId = resultSet.getString(1);
            return splitOrderId(currentOrderId);
        }
        return splitOrderId(null);
    }

    public String splitOrderId(String currentOrderId) {    //O008
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("O");
            int id = Integer.parseInt(split[1]);    //008
            id++;  //9
            return "O00" + id;
        }
        return "O001";
    }
}
