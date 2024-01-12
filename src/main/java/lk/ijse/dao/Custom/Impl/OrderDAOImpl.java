package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.OrderDAO;

import java.time.LocalDate;

public class OrderDAOImpl  implements OrderDAO {

    @Override
    public boolean save(String orderId, String cusId, LocalDate date) {
        return false;
    }
}
