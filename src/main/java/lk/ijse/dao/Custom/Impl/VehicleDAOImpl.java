package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.VehicleDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.VehicleDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl  implements VehicleDAO {
    @Override
    public List<VehicleDto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from vehicle");
        ArrayList<VehicleDto> objects = new ArrayList<>();

        while (resultSet.next()){
            objects.add(
                    new VehicleDto(
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
    public boolean save(VehicleDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO Vehicle VALUES(?, ?, ?, ?, ?)",
                dto.getId(), dto.getModel(),dto.getYear(),dto.getLicensePlate(),dto.getCustomerID());

    }

    @Override
    public boolean update(VehicleDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE vehicle SET   Model = ?, Year = ?,LicensePlate = ? ,CustomerID = ? WHERE VehicleID = ?",
                dto.getId(),dto.getModel(),dto.getYear(),dto.getLicensePlate(),dto.getCustomerID());

    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Vehicle WHERE VehicleID = ?",id);
    }

    @Override
    public VehicleDto search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM vehicle WHERE VehicleID = ?",id);

        VehicleDto dto = null;

        if(resultSet.next()) {
            dto = new VehicleDto(
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
        ResultSet resultSet = SQLUtil.execute("SELECT VehicleID FROM vehicle ORDER BY VehicleID DESC LIMIT 1");

        String currentID = null;

        if (resultSet.next()){
            currentID = resultSet.getString(1);
            return splitVehicleID(currentID);
        }
        return splitVehicleID(null);
    }

    @Override
    public String splitID(String currentID) {
        return null;
    }

    @Override
    public String splitVehicleID(String currentAppointmentID) {
        if (currentAppointmentID != null ){
            String [] split = currentAppointmentID.split("V");
            int id = Integer.parseInt(split[1]);
            id++;
            return "V00"+id;
        }
        return "V001";
    }


    @Override
    public String getLastId() throws SQLException {
        return null;
    }

    @Override
    public List<VehicleDto> loadAllVehicles() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM vehicle");

        List<VehicleDto> vehicleList = new ArrayList<>();

        while(resultSet.next()){
            vehicleList.add(new VehicleDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return vehicleList;
    }
}
