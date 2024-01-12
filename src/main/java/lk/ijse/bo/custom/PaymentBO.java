package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.paymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    paymentDto searchPayment(String code) throws SQLException;

    ArrayList<paymentDto> getAll() throws SQLException;

    boolean savePayment(paymentDto dto) throws SQLException;

    boolean deletePayment(String id) throws SQLException;

    boolean updatePayment(paymentDto dto) throws SQLException;

    String generateNextID() throws SQLException;

    String splitID(String currentID);
}
