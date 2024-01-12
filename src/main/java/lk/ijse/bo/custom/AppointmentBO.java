package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.appoinmentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentBO extends SuperBO {

    appoinmentDto searchAppointment(String code) throws SQLException;

    ArrayList<appoinmentDto> getAll() throws SQLException;

    boolean saveAppoinment(appoinmentDto dto) throws SQLException;

    boolean deleteAppoinment(String id) throws SQLException;

    boolean updateAppoinment(appoinmentDto dto) throws SQLException;

    String generateNextAppointmentID() throws SQLException;

    String splitAppointmentID(String currentAppointmentID);

    String getAppointmentCount() throws SQLException;

    boolean getExit(String date, String time) throws SQLException;

}
