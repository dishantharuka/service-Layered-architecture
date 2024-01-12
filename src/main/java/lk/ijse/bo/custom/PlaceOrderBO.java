package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.PlaceOrderDto;
import lk.ijse.dto.itemDto;

import java.sql.SQLException;

public interface PlaceOrderBO extends SuperBO {
    itemDto searchItem(String code) throws SQLException;

    CustomerDto searchCustomer(String id) throws SQLException;

    public boolean placeOrder(PlaceOrderDto pDto) throws SQLException;

}
