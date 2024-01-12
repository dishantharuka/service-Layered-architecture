package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.RecordDto;
import lk.ijse.dto.VehicleDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RecordBO extends SuperBO {

    RecordDto searchAppointment(String code) throws SQLException;

    ArrayList<RecordDto> getAll() throws SQLException;

    boolean saveRecord(RecordDto dto) throws SQLException;

    boolean deleteRecord(String id) throws SQLException;

    boolean updateRecord(RecordDto dto) throws SQLException;

    String generateNextRecordId() throws SQLException;

    String splitRecordID(String currentID);

    ArrayList<String> getTechnicianNames() throws SQLException;


    List<VehicleDto> getAllVehicles() throws SQLException;

}
