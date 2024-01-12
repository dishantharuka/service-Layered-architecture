package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.paymentDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements CrudDAO<paymentDto> {

    @Override
    public List<paymentDto> getAll() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("select * from payment");
        ArrayList<paymentDto> objects = new ArrayList<>();

        while (resultSet.next()){
            objects.add(
                    new paymentDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return objects;
    }

    @Override
    public boolean save(paymentDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO payment VALUES(?, ?, ?, ?)",
                dto.getId(),dto.getAmount(),dto.getPaymentMethod(),dto.getItemID());
    }

    @Override
    public boolean update(paymentDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE payment SET ItemID = ?, Amount = ?,PaymentMethod = ? WHERE  PaymentID= ?",dto.getId(),dto.getItemID(),dto.getAmount(),dto.getPaymentMethod());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM payment WHERE PaymentID = ?",id);
    }

    @Override
    public paymentDto search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM payment WHERE PaymentID = ?",id);

        paymentDto dto = null;

        if(resultSet.next()) {
            dto = new paymentDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return dto;

    }

    @Override
    public String generateNextID() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT PaymentID FROM payment ORDER BY PaymentID DESC LIMIT 1");

        String currenttID = null;

        if (resultSet.next()){
            currenttID = resultSet.getString(1);
            return splitID(currenttID);
        }
        return splitID(null);
    }

    @Override
    public String splitID(String currentID) {
        if (currentID != null ){
            String [] split = currentID.split("P");
            int id = Integer.parseInt(split[1]);
            id++;
            return "P00"+id;
        }
        return "P001";

    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }
}
