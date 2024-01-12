package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.itemDto;
import lk.ijse.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {


    itemDto searchItem(String code) throws SQLException;

    itemDto search(String id) throws SQLException;

    ArrayList<itemDto> getAll() throws SQLException;

    boolean saveItem(itemDto dto) throws SQLException;

    boolean deleteItem(String id) throws SQLException;

    boolean updateItem(itemDto dto) throws SQLException;

    boolean updateQty(CartTm cartTm) throws SQLException;

    String generateNextId() throws SQLException;

    String splitRecordID(String currentID);
}
