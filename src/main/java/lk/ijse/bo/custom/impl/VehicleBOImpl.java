package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.VehicleBO;
import lk.ijse.dao.Custom.Impl.VehicleDAOImpl;
import lk.ijse.dao.Custom.VehicleDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.VehicleDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleBOImpl implements VehicleBO {

    VehicleDAO vehicleDAO= new VehicleDAOImpl();
    public  VehicleDto searchVehicle(String code) throws SQLException {
        return vehicleDAO.search(code);
    }

    public boolean saveVehicle(VehicleDto dto) throws SQLException {

        return vehicleDAO.save(dto);
    }

    public boolean deleteVehicle(String id) throws SQLException {

        return vehicleDAO.delete(id);
    }

    public boolean updateVehicle(VehicleDto dto) throws SQLException {

        return vehicleDAO.update(dto);


    }
    public ArrayList<VehicleDto> getAll() throws SQLException {

            return (ArrayList<VehicleDto>) vehicleDAO.getAll();
    }

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

    public String generateNextVehicleID() throws SQLException {

            ResultSet resultSet = SQLUtil.execute("SELECT VehicleID FROM vehicle ORDER BY VehicleID DESC LIMIT 1");

            String currentID = null;

            if (resultSet.next()){
                currentID = resultSet.getString(1);
                return splitVehicleID(currentID);
            }
            return splitVehicleID(null);
        }

        public String splitVehicleID(String currentAppointmentID) {
            if (currentAppointmentID != null ){
                String [] split = currentAppointmentID.split("V");
                int id = Integer.parseInt(split[1]);
                id++;
                return "V00"+id;
            }
            return "V001";
        }



}


