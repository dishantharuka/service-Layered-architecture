package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.SupplierDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.SupplierDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public List<SupplierDto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from supplier");
        ArrayList<SupplierDto> objects = new ArrayList<>();

        while (resultSet.next()){
            objects.add(
                    new SupplierDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return objects;
    }

    @Override
    public boolean save(SupplierDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO supplier VALUES(?, ?, ?, ?, ?)",
                dto.getSupplierId(),dto.getSupplierName(),dto.getContactInformation(),dto.getAddress(),dto.getUserID() );

    }

    @Override
    public boolean update(SupplierDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE supplier SET SupplierName = ? ,ContactInformation  = ?, Address = ?, UserID = ? WHERE SupplierID = ?",
                dto.getSupplierId(),dto.getSupplierName(),dto.getContactInformation(),dto.getAddress(),dto.getUserID());

    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM supplier WHERE SupplierID = ?",id);
    }

    @Override
    public SupplierDto search(String id) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier WHERE SupplierID = ?",id);

        SupplierDto dto = null;

        if(resultSet.next()) {
            dto = new SupplierDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return dto;
    }

    @Override
    public String generateNextID() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT SupplierID FROM supplier ORDER BY SupplierID DESC LIMIT 1");

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
            String [] split = currentID.split("S");
            int id = Integer.parseInt(split[1]);
            id++;
            return "S00"+id;
        }
        return "S001";
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }
}
