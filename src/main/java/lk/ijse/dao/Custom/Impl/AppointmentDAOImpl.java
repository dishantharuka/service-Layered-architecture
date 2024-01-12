package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.AppointmentDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.appoinmentDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public List<appoinmentDto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from appointment");
        ArrayList<appoinmentDto> objects = new ArrayList<>();

        while (resultSet.next()){
            objects.add(
                    new appoinmentDto(
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
    public boolean save(appoinmentDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO appointment VALUES(?, ?, ?, ?, ?)",dto.getId(),dto.getDate(),dto.getTime(),dto.getServiceType(),dto.getVehicleID());
    }

    @Override
    public boolean update(appoinmentDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE appointment SET Date = ?, Time = ?, ServiceType = ?, VehicleID = ? WHERE AppointmentID = ?",
                dto.getId(),dto.getDate(),dto.getTime(),dto.getServiceType(),dto.getVehicleID());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM appointment WHERE AppointmentID = ?",id);
    }

    @Override
    public appoinmentDto search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM appointment WHERE AppointmentID = ?",id);

        appoinmentDto dto = null;

        if(resultSet.next()) {
            dto = new appoinmentDto(
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
        return null;
    }

    @Override
    public String splitID(String currentID) {
        return null;
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }
    public String getAppointmentCount() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) as row_count FROM appointment");

        String Count = null;
        if (resultSet.next()){
            Count = resultSet.getString(1);
        }
        return Count;
    }

    public boolean getExit(String date, String time) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM appointment WHERE Date = ? AND Time = ?",date,time);
        if (resultSet.next()){
            return false;
        }
        else{
            return true;
        }

    }
     public String splitAppointmentID(String currentAppointmentID) {
        if (currentAppointmentID != null ){
            String [] split = currentAppointmentID.split("A");
            int id = Integer.parseInt(split[1]);
            id++;
            return "A00"+id;
        }
        return "A001";
    }
    public String generateNextAppointmentID() throws SQLException {

        ResultSet resultSet =SQLUtil.execute ("SELECT AppointmentID FROM appointment ORDER BY AppointmentID DESC LIMIT 1");

        String currentAppointmentID = null;

        if (resultSet.next()){
            currentAppointmentID = resultSet.getString(1);
            return splitAppointmentID(currentAppointmentID);
        }
        return splitAppointmentID(null);
    }

}
