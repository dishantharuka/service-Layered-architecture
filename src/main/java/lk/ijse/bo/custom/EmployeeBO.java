package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.Employeedto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    Employeedto searchEmployee(String code) throws SQLException;

    ArrayList<Employeedto> getAll() throws SQLException;

    boolean saveEmployee(Employeedto dto) throws SQLException;

    boolean deleteEmployee(String id) throws SQLException;

    boolean updateEmployee(Employeedto dto) throws SQLException;

    String generateNextEmpID() throws SQLException;

    String splitID(String currentID);

    ArrayList<String> getTechnicianNames() throws SQLException;

}
