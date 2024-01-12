package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.SalaryDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.SalaryDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public List<SalaryDto> getAll() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("select * from salary");
        ArrayList<SalaryDto> objects = new ArrayList<>();

        while (resultSet.next()){
            objects.add(
                    new SalaryDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return objects;
    }

    @Override
    public boolean save(SalaryDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO salary VALUES(?, ?, ?, ?, ?)",
                dto.getSalaryId(),dto.getEmployeeID(),dto.getMonth(),dto.getYear(),dto.getAmount());

    }

    @Override
    public boolean update(SalaryDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE salary SET EmployeeID = ? ,Month  = ?, Year = ?, Amount = ? WHERE SalaryID = ?",
                dto.getSalaryId(),dto.getEmployeeID(),dto.getMonth(),dto.getYear(),dto.getAmount());


    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM salary WHERE SalaryID = ?",id);
    }

    @Override
    public SalaryDto search(String id) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM salary WHERE SalaryID = ?",id);

        SalaryDto dto = null;

        if(resultSet.next()) {
            dto = new SalaryDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return dto;
    }

    @Override
    public String generateNextID() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT SalaryID FROM salary ORDER BY SalaryID DESC LIMIT 1");

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
            String [] split = currentID.split("S");
            int id = Integer.parseInt(split[1]);
            id++;
            return "S00"+id;
        }
        return "S001";
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }
}
