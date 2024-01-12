package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dao.Custom.Impl.ItemDAOImpl;
import lk.ijse.dao.Custom.ItemDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.itemDto;
import lk.ijse.dto.tm.CartTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = new ItemDAOImpl();

     public itemDto searchItem(String code) throws SQLException {

        return itemDAO.search(code);

    }

    public itemDto search(String id) throws SQLException {

        return itemDAO.search(id);
    }

    public ArrayList<itemDto> getAll() throws SQLException {

        return (ArrayList<itemDto>) itemDAO.getAll();
    }

    public boolean saveItem(itemDto dto) throws SQLException {

        return itemDAO.save(dto);
    }

    public boolean deleteItem(String id) throws SQLException {

        return itemDAO.delete(id);
    }

    public boolean updateItem(itemDto dto) throws SQLException {

        return itemDAO.updateItem((List<CartTm>) dto);

    }


    public boolean updateItem(List<CartTm> tmList) throws SQLException {
        for (CartTm cartTm : tmList) {
            if (!updateQty(cartTm)) {
                return false;
            }
        }
        return true;
    }

    public boolean updateQty(CartTm cartTm) throws SQLException {

        return SQLUtil.execute("UPDATE inventoryItem SET Quantity = Quantity - ? WHERE ItemID = ?",
                cartTm.getQty(), cartTm.getCode());
    }

    public String generateNextId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT ItemID FROM inventoryItem ORDER BY ItemID DESC LIMIT 1");

        String currentID = null;

        if (resultSet.next()) {
            currentID = resultSet.getString(1);
            return splitRecordID(currentID);
        }
        return "I001";
    }

    public String splitRecordID(String currentID) {
        if (currentID != null) {
            String[] split = currentID.split("I");
            int id = Integer.parseInt(split[1]);
            id++;
            return "I00" + id;
        }
        return "I001";
    }

}

