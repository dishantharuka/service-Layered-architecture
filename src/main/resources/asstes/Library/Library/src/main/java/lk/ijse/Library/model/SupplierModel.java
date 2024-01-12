package lk.ijse.Library.model;

import lk.ijse.Library.db.DbConnection;
import lk.ijse.Library.dto.SupplierDto;
import lk.ijse.Library.dto.tm.SupplierTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {

    public boolean saveSupplier(final SupplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO supplier VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getS_id());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setObject(4, dto.getTel());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean updateSupplier(final SupplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE supplier SET s_name = ?, address = ?, tel = ? WHERE S_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setObject(3, dto.getTel());
        pstm.setString(4, dto.getS_id());

        return pstm.executeUpdate() > 0;
    }

    public boolean deleteSupplier(String S_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM supplier WHERE S_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, S_id);

        return pstm.executeUpdate() > 0;
    }

    public List<SupplierTm> getAllSupplier() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<SupplierTm> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String cus_name = resultSet.getString(2);
            String cus_address = resultSet.getString(3);
            int cus_tel = Integer.parseInt(resultSet.getString(4));

            var dto = new SupplierTm(cus_id, cus_name, cus_address, cus_tel);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
