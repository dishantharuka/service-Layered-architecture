package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.Custom.CustomerDAO;
import lk.ijse.dao.Custom.Impl.CustomerDAOImpl;
import lk.ijse.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO=new CustomerDAOImpl();
    public  CustomerDto searchCustomer(String code) throws SQLException {

        return customerDAO.search(code);

    }


    public ArrayList<CustomerDto> getAll() throws SQLException {

        return (ArrayList<CustomerDto>) customerDAO.getAll();

    }

    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        return customerDAO.save(dto);
    }

    public boolean deleteCustomer(String id) throws SQLException {

        return customerDAO.delete(id);
    }

    public boolean updateCustomer(CustomerDto dto) throws SQLException {

        return customerDAO.update(dto);

    }

    public String getCustomerCount() throws SQLException {

        return customerDAO.getCustomerCount();
    }

    public String generateNextID() throws SQLException {

        return customerDAO.generateNextID();
    }

    public String splitID(String currentID) {
        if (currentID != null) {
            String[] split = currentID.split("C");
            int id = Integer.parseInt(split[1]);
            id++;
            return "C00" + id;
        }
        return "C001";
    }

}

