package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dao.Custom.Impl.PaymentDAOImpl;
import lk.ijse.dao.Custom.PaymentDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.paymentDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO= (PaymentDAO) new PaymentDAOImpl();

    public  paymentDto searchPayment(String code) throws SQLException {

        return paymentDAO.search(code);

    }



    public ArrayList<paymentDto> getAll() throws SQLException {

            return paymentDAO.getAll();
    }
    public boolean savePayment( paymentDto dto) throws SQLException {

        return paymentDAO.save(dto);
    }

    public boolean deletePayment(String id) throws SQLException {

        return paymentDAO.delete(id);
    }

    public boolean updatePayment(paymentDto dto) throws SQLException {

        return paymentDAO.update(dto);

    }

    public String generateNextID() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT PaymentID FROM payment ORDER BY PaymentID DESC LIMIT 1");

        String currenttID = null;

        if (resultSet.next()){
            currenttID = resultSet.getString(1);
            return splitID(currenttID);
        }
        return splitID(null);
    }

    public String splitID(String currentID) {
        if (currentID != null ){
            String [] split = currentID.split("P");
            int id = Integer.parseInt(split[1]);
            id++;
            return "P00"+id;
        }
        return "P001";

    }
}

