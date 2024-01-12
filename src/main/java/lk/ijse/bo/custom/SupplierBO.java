package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
SupplierDto searchAppointment(String code) throws SQLException;
        ArrayList<SupplierDto> getAll() throws SQLException;
         boolean saveSupplier(SupplierDto dto) throws SQLException;
        boolean deleteSupplier(String id) throws SQLException ;
        boolean updateSupplier(SupplierDto dto) throws SQLException;
        String generateNextID() throws SQLException ;
        String splitID(String currentID);
}
