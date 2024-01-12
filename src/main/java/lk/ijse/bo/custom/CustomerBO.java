package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    CustomerDto searchCustomer(String code) throws SQLException;

    ArrayList<CustomerDto> getAll() throws SQLException;

    boolean saveCustomer(CustomerDto dto) throws SQLException;

    boolean deleteCustomer(String id) throws SQLException;

    boolean updateCustomer(CustomerDto dto) throws SQLException;

    String getCustomerCount() throws SQLException;

    String generateNextID() throws SQLException;

    String splitID(String currentID);


}
