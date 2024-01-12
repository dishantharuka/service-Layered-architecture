package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.AppointmentBO;
import lk.ijse.dao.Custom.AppointmentDAO;
import lk.ijse.dao.Custom.Impl.AppointmentDAOImpl;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.appoinmentDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppoinmentBOImpl implements AppointmentBO {

    AppointmentDAO appointmentDAO=new AppointmentDAOImpl();

    public  appoinmentDto searchAppointment(String code) throws SQLException {

       return appointmentDAO.search(code);

    }

    public ArrayList<appoinmentDto> getAll() throws SQLException {
        return (ArrayList<appoinmentDto>) appointmentDAO.getAll();
    }


    public  boolean saveAppoinment(appoinmentDto dto) throws SQLException {

        return appointmentDAO.save(dto);
    }

    public boolean deleteAppoinment(String id) throws SQLException {
        return appointmentDAO.delete(id);
    }

    public boolean updateAppoinment(appoinmentDto dto) throws SQLException {

        return appointmentDAO.update(dto);

    }

    public String generateNextAppointmentID() throws SQLException {
        return appointmentDAO.generateNextID();
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
}

