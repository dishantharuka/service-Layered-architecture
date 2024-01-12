package lk.ijse.dao.Custom;

import lk.ijse.dto.paymentDto;

import java.util.ArrayList;

public interface PaymentDAO {

    paymentDto search(String code);

    ArrayList<paymentDto> getAll();

    boolean save(paymentDto dto);

    boolean delete(String id);

    boolean update(paymentDto dto);
}
