package lk.ijse.dao.Custom;

import lk.ijse.dao.SuperDAO;

import java.time.LocalDate;

public interface OrderDAO extends SuperDAO {


    boolean save(String orderId, String cusId, LocalDate date);
}
