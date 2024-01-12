package lk.ijse.dao.Custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.itemDto;
import lk.ijse.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<itemDto> {

    boolean updateItem(List<CartTm> tmList) throws SQLException;

    boolean updateQty(CartTm cartTm) throws SQLException;

    String generateNextId() throws SQLException;

    String splitRecordID(String currentID);
}
