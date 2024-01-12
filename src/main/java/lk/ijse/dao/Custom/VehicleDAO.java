package lk.ijse.dao.Custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.VehicleDto;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDAO extends CrudDAO<VehicleDto> {

    String splitVehicleID(String currentAppointmentID);

    List<VehicleDto> loadAllVehicles() throws SQLException;
}
