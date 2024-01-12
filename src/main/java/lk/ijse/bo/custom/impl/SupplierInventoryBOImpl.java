package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SupplierInventoryBO;
import lk.ijse.dao.Custom.Impl.SupplierInventoryDAOImpl;
import lk.ijse.dao.Custom.SupplierInventoryDAO;
import lk.ijse.dto.SupplierInventoryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierInventoryBOImpl implements SupplierInventoryBO {

    SupplierInventoryDAO supplierInventoryDAO=new SupplierInventoryDAOImpl();

    public  SupplierInventoryDto searchAppointment(String code) throws SQLException {

        return supplierInventoryDAO.search(code);
    }

        public  ArrayList<SupplierInventoryDto> getAll() throws SQLException {

                return (ArrayList<SupplierInventoryDto>) supplierInventoryDAO.getAll();
        }
        public  boolean saveSupplierInventory(SupplierInventoryDto dto) throws SQLException {

            return supplierInventoryDAO.save(dto);
        }

        public boolean deleteSupplierInventory(String SupplierID) throws SQLException {

            return supplierInventoryDAO.delete(SupplierID);
        }

        public boolean updateSupplierInventory(SupplierInventoryDto dto) throws SQLException {

            return supplierInventoryDAO.update(dto);

        }
    }