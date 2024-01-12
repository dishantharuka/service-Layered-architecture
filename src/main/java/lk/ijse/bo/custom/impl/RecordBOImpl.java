package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.RecordBO;
import lk.ijse.dao.Custom.EmployeeDAO;
import lk.ijse.dao.Custom.RecordDAO;
import lk.ijse.dao.Custom.VehicleDAO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.RecordDto;
import lk.ijse.dto.VehicleDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordBOImpl implements RecordBO {

    RecordDAO recordDAO= (RecordDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RECORD);
    EmployeeDAO employeeDAO  = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);

    public  RecordDto searchAppointment(String code) throws SQLException {

        return recordDAO.search(code);
    }

    public ArrayList<RecordDto> getAll() throws SQLException {

            return (ArrayList<RecordDto>) recordDAO.getAll();

    }
    public boolean saveRecord( RecordDto dto) throws SQLException {

        return recordDAO.save(dto);
    }

    public boolean deleteRecord(String id) throws SQLException {

        return recordDAO.delete(id);
    }

    public boolean updateRecord(RecordDto dto) throws SQLException {

        return recordDAO.update(dto);

    }

    public String generateNextRecordId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT RecordID FROM servicerecord ORDER BY RecordID DESC LIMIT 1");

        String currentID = null;

        if (resultSet.next()){
            currentID = resultSet.getString(1);
            return splitRecordID(currentID);
        }
        return "R001";
    }

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
    public ArrayList<String> getTechnicianNames() throws SQLException {
        return employeeDAO.getTechnicianNames();
    }

    @Override
    public List<VehicleDto> getAllVehicles() throws SQLException {
        return vehicleDAO.loadAllVehicles();
    }
}

