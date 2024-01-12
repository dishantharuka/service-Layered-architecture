package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.VehicleDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleBO extends SuperBO {

         VehicleDto searchVehicle(String code) throws SQLException;

        boolean saveVehicle(VehicleDto dto) throws SQLException;

         boolean deleteVehicle(String id) throws SQLException ;


         boolean updateVehicle(VehicleDto dto) throws SQLException ;

        ArrayList<VehicleDto> getAll() throws SQLException;
        List<VehicleDto> loadAllVehicles() throws SQLException;
        String generateNextVehicleID() throws SQLException ;
        String splitVehicleID(String currentAppointmentID) ;



}
