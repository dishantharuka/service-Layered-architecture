package lk.ijse.dao.Custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.CustomerDto;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<CustomerDto> {

    String getCustomerCount() throws SQLException;
}

