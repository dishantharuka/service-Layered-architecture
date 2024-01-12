package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dao.Custom.EmployeeDAO;
import lk.ijse.dao.Custom.Impl.EmployeeDAOImpl;
import lk.ijse.dto.Employeedto;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO=new EmployeeDAOImpl();

    public  Employeedto searchEmployee(String code) throws SQLException {

        return employeeDAO.search(code);


    }


    public ArrayList<Employeedto> getAll() throws SQLException {

            return (ArrayList<Employeedto>) employeeDAO.getAll();
    }

    public boolean saveEmployee(Employeedto dto) throws SQLException {

        return employeeDAO.save(dto);
    }

    public boolean deleteEmployee(String id) throws SQLException {
        return employeeDAO.delete(id);
    }

    public boolean updateEmployee(Employeedto dto) throws SQLException {

        return employeeDAO.update(dto);

    }

    public String generateNextEmpID() throws SQLException {

            return employeeDAO.generateNextID();
        }

         public String splitID(String currentID) {
            if (currentID != null ){
                String [] split = currentID.split("E");
                int id = Integer.parseInt(split[1]);
                id++;
                return "E00"+id;
            }
            return "E001";
        }

    public ArrayList<String> getTechnicianNames() throws SQLException {

            return employeeDAO.getTechnicianNames();
    }
}


