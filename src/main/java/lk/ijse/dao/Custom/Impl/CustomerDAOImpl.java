package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.CustomerDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.CustomerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<CustomerDto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from customer");
        ArrayList<CustomerDto> objects = new ArrayList<>();

        while (resultSet.next()) {
            objects.add(
                    new CustomerDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return objects;

    }

    @Override
    public boolean save(CustomerDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ?, ?, ?)",
                dto.getId(), dto.getName(), dto.getTel(), dto.getEmail(), dto.getAddress(), dto.getUserId());

    }

    @Override
    public boolean update(CustomerDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE Customer SET  Name = ?, PhoneNumber = ?, Email = ?,Address = ? , UserId = ? WHERE CustomerID = ?",
                dto.getId(), dto.getName(), dto.getTel(), dto.getEmail(), dto.getAddress(), dto.getUserId());


    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM customer WHERE CustomerID = ?", id);
    }

    @Override
    public CustomerDto search(String id) throws SQLException {
        return null;
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }

    @Override
    public String getCustomerCount() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) as row_count FROM customer");

        String customerCount = null;
        if (resultSet.next()) {
            customerCount = resultSet.getString(1);
        }
        return customerCount;
    }

    @Override
    public String generateNextID() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT CustomerID FROM customer ORDER BY CustomerID DESC LIMIT 1");

        String currenttID = null;

        if (resultSet.next()) {
            currenttID = resultSet.getString(1);
            return splitID(currenttID);
        }
        return splitID(null);
    }

    @Override
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
