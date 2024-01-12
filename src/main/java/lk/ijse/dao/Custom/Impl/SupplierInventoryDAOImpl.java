package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.SupplierInventoryDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.SupplierInventoryDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierInventoryDAOImpl implements SupplierInventoryDAO {
    @Override
    public List<SupplierInventoryDto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from SupplierInventory");
        ArrayList<SupplierInventoryDto> objects = new ArrayList<>();

        while (resultSet.next()){
            objects.add(
                    new SupplierInventoryDto(
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
    public boolean save(SupplierInventoryDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO SupplierInventory VALUES(?, ?, ?, ?)",
                dto.getSupplierID(),dto.getItemID(),dto.getDate(),dto.getTime());

    }

    @Override
    public boolean update(SupplierInventoryDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE SupplierInventory SET ItemID = ? ,Date  = ?, Time = ? WHERE SupplierID = ?",
                dto.getSupplierID(),dto.getItemID(),dto.getDate(),dto.getTime());

    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM SupplierInventory WHERE SupplierID = ?",id);

    }

    @Override
    public SupplierInventoryDto search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplierinventory WHERE SupplierID = ?",id);

        SupplierInventoryDto dto = null;

        if(resultSet.next()) {
            dto = new SupplierInventoryDto(
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
        return null;
    }

    @Override
    public String splitID(String currentID) {
        return null;
    }


    @Override
    public String getLastId() throws SQLException {
        return null;
    }
}
