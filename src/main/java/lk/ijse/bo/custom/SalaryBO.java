package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.SalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {
     SalaryDto searchAppointment(String code) throws SQLException;

    ArrayList<SalaryDto> getAll() throws SQLException;

    boolean saveSalary(SalaryDto dto) throws SQLException;

    boolean deleteSalary(String id) throws SQLException;

    boolean updateSalary(SalaryDto dto) throws SQLException;

    String generateNextSalaryID() throws SQLException;

    String splitID(String currentID);
}
