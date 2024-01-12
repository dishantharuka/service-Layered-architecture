package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dao.Custom.Impl.SupplierDAOImpl;
import lk.ijse.dao.Custom.SupplierDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.SupplierDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO=new SupplierDAOImpl();

    public  SupplierDto searchAppointment(String code) throws SQLException {

        return supplierDAO.search(code);
    }


    public ArrayList<SupplierDto> getAll() throws SQLException {

            return (ArrayList<SupplierDto>) supplierDAO.getAll();
    }
    public  boolean saveSupplier(SupplierDto dto) throws SQLException {

        return supplierDAO.save(dto);
    }

    public boolean deleteSupplier(String id) throws SQLException {

        return supplierDAO.delete(id);
    }

    public boolean updateSupplier(SupplierDto dto) throws SQLException {

        return supplierDAO.update(dto);
    }


    public String generateNextID() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT SupplierID FROM supplier ORDER BY SupplierID DESC LIMIT 1");

        String currenttID = null;

        if (resultSet.next()){
            currenttID = resultSet.getString(1);
            return splitID(currenttID);
        }
        return splitID(null);
    }

     public String splitID(String currentID) {
        if (currentID != null ){
            String [] split = currentID.split("S");
            int id = Integer.parseInt(split[1]);
            id++;
            return "S00"+id;
        }
        return "S001";
    }
    }

