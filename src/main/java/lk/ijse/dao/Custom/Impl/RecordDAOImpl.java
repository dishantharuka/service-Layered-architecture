package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.RecordDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.RecordDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordDAOImpl implements RecordDAO {
    @Override
    public List<RecordDto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from servicerecord");
        ArrayList<RecordDto> objects = new ArrayList<>();

        while (resultSet.next()) {
            objects.add(
                    new RecordDto(
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
    public boolean save(RecordDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO servicerecord VALUES(?, ?, ?, ?, ?)",
                dto.getId(), dto.getVehicleID(), dto.getServiceDate(), dto.getServiceDescription(), dto.getTechnicianName());

    }

    @Override
    public boolean update(RecordDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE servicerecord SET VehicleID  = ?, ServiceDate = ?, ServiceDescription = ?,TechnicianName = ? WHERE RecordID = ?",
                dto.getId(), dto.getVehicleID(), dto.getServiceDate(), dto.getServiceDescription(), dto.getTechnicianName());


    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM servicerecord WHERE RecordID = ?", id);
    }

    @Override
    public RecordDto search(String id) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM servicerecord WHERE RecordID = ?", id);

        RecordDto dto = null;

        if (resultSet.next()) {
            dto = new RecordDto(
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

        ResultSet resultSet = SQLUtil.execute("SELECT RecordID FROM servicerecord ORDER BY RecordID DESC LIMIT 1");

        String currentID = null;

        if (resultSet.next()) {
            currentID = resultSet.getString(1);
            return splitRecordID(currentID);
        }
        return "R001";
    }

    @Override
    public String splitID(String currentID) {
        return null;
    }

    @Override
    public String splitRecordID(String currentID) {
        if (currentID != null) {
            String[] split = currentID.split("R");
            int id = Integer.parseInt(split[1]);
            id++;
            return "R00" + id;
        }
        return "R001";
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }
}