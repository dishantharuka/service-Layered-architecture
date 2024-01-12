package lk.ijse.Library.model;

import lk.ijse.Library.db.DbConnection;
import lk.ijse.Library.dto.DonationDto;
import lk.ijse.Library.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DonationModel {

    public boolean saveDonation(final DonationDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO donation VALUES(?, ?, ?, ?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getD_id());
        pstm.setString(2, dto.getD_name());
        pstm.setString(3, dto.getAddress());
        pstm.setObject(4, dto.getTel());
        pstm.setObject(5,dto.getMonetary_amount());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean updateDonation(final DonationDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE supplier SET D_name = ?, Address = ?, Tel = ?,Monetary_amount= ? WHERE D_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getD_name());
        pstm.setString(2, dto.getAddress());
        pstm.setObject(3, dto.getTel());
        pstm.setObject(4, dto.getMonetary_amount());
        pstm.setString(5, dto.getD_id());

        return pstm.executeUpdate() > 0;
    }

    public boolean deleteDonation(String d_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM donation WHERE d_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, d_id);

        return pstm.executeUpdate() > 0;
    }

}
