package lk.ijse.dao.Custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.Employeedto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employeedto> {

    String generateNextEmpID() throws SQLException;

    ArrayList<String> getTechnicianNames() throws SQLException;
}

