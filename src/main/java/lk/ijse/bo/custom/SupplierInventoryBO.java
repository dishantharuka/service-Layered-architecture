package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.SupplierInventoryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierInventoryBO extends SuperBO {

    SupplierInventoryDto searchAppointment(String code) throws SQLException;

    ArrayList<SupplierInventoryDto> getAll() throws SQLException;

    boolean saveSupplierInventory(SupplierInventoryDto dto) throws SQLException;

    boolean deleteSupplierInventory(String SupplierID) throws SQLException;

    boolean updateSupplierInventory(SupplierInventoryDto dto) throws SQLException;
}
