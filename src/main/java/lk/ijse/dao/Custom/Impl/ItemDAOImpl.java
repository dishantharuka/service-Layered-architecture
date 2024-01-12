package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.ItemDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.itemDto;
import lk.ijse.dto.tm.CartTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public List<itemDto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from inventoryitem");
        ArrayList<itemDto> objects = new ArrayList<>();

        while (resultSet.next()){
            objects.add(
                    new itemDto(
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
    public boolean save(itemDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO inventoryItem VALUES(?, ?, ?, ?)",dto.getId(),dto.getName(),dto.getQty(),dto.getUniPrice());

    }

    @Override
    public boolean update(itemDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE inventoryItem SET  ItemName = ?, Quantity = ?, UnitPrice = ? WHERE ItemID = ?",
                dto.getId(),dto.getName(),dto.getQty(),dto.getUniPrice());

    }

    @Override
    public boolean updateItem(List<CartTm> tmList) throws SQLException {
        for (CartTm cartTm : tmList) {
            if(!updateQty(cartTm)) {
                return false;
            }
        }
        return true;
    }

    @Override
     public boolean updateQty(CartTm cartTm) throws SQLException {

        return SQLUtil.execute("UPDATE inventoryItem SET Quantity = Quantity - ? WHERE ItemID = ?",
                cartTm.getQty(),cartTm.getCode());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM inventoryItem WHERE ItemID = ?",id);
    }

    @Override
    public itemDto search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM inventoryitem WHERE ItemID = ?",id);

        itemDto dto = null;

        if(resultSet.next()) {
            dto = new itemDto(
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
    public String generateNextId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT ItemID FROM inventoryItem ORDER BY ItemID DESC LIMIT 1");

        String currentID = null;

        if (resultSet.next()){
            currentID = resultSet.getString(1);
            return splitRecordID(currentID);
        }
        return "I001";
    }
@Override
     public String splitRecordID(String currentID) {
        if (currentID != null ){
            String [] split = currentID.split("I");
            int id = Integer.parseInt(split[1]);
            id++;
            return "I00"+id;
        }
        return "I001";
    }
    @Override
    public String getLastId() throws SQLException {
        return null;
    }
}
