package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SalaryBO;
import lk.ijse.dao.Custom.Impl.SalaryDAOImpl;
import lk.ijse.dao.Custom.SalaryDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.SalaryDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {

    SalaryDAO salaryDAO= new SalaryDAOImpl();

    public  SalaryDto searchAppointment(String code) throws SQLException {

        return salaryDAO.search(code);

    }

    public ArrayList<SalaryDto> getAll() throws SQLException {

            return (ArrayList<SalaryDto>) salaryDAO.getAll();
    }
    public  boolean saveSalary(SalaryDto dto) throws SQLException {

        return salaryDAO.save(dto);
    }

    public boolean deleteSalary(String id) throws SQLException {

        return salaryDAO.delete(id);
    }

    public boolean updateSalary(SalaryDto dto) throws SQLException {

        return salaryDAO.update(dto);
    }


    public String generateNextSalaryID() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT SalaryID FROM salary ORDER BY SalaryID DESC LIMIT 1");

        String currenttID = null;

        if (resultSet.next()){
            currenttID = resultSet.getString(1);
            return splitID(currenttID);
        }
        return splitID(null);
    }

     public String splitID(String currentID) {
        if (currentID != null ){
            String [] split = currentID.split("S");
            int id = Integer.parseInt(split[1]);
            id++;
            return "S00"+id;
        }
        return "S001";
    }

}

