package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.EmployeeDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.Employeedto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public List<Employeedto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from employee");
        ArrayList<Employeedto> objects = new ArrayList<>();

        while (resultSet.next()){
            objects.add(
                    new Employeedto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return objects;
    }

    @Override
    public boolean save(Employeedto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?)",
                dto.getEmployeeId(),dto.getName(),dto.getContactInformation(),dto.getRole(),dto.getSalary(),dto.getUserId());

    }

    @Override
    public boolean update(Employeedto dto) throws SQLException {
        return SQLUtil.execute("UPDATE employee SET Name = ?, ContactInformation = ?, Role = ?, Salary = ?,UserID = ? WHERE EmployeeID = ?",
                dto.getEmployeeId(),dto.getName(),dto.getContactInformation(),dto.getRole(),dto.getSalary(),dto.getUserId() );

    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM employee WHERE EmployeeID = ?",id);
    }

    @Override
    public Employeedto search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee WHERE EmployeeID = ?",id);

        Employeedto dto = null;

        if(resultSet.next()) {
            dto = new Employeedto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
        return dto;
    }

    @Override
    public String generateNextID() throws SQLException {
        return null;
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }
@Override
    public String generateNextEmpID() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT EmployeeID FROM employee ORDER BY EmployeeID DESC LIMIT 1");

        String currenttID = null;

        if (resultSet.next()){
            currenttID = resultSet.getString(1);
            return splitID(currenttID);
        }
        return splitID(null);
    }

    @Override
     public String splitID(String currentID) {
        if (currentID != null ){
            String [] split = currentID.split("E");
            int id = Integer.parseInt(split[1]);
            id++;
            return "E00"+id;
        }
        return "E001";
    }
@Override
    public ArrayList<String> getTechnicianNames() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("select Name from employee where Role = ?");
        ArrayList<String> technicianNames = new ArrayList<>();

        while (resultSet.next()){
            technicianNames.add(resultSet.getString(1));
        }
        return technicianNames;
    }
}
