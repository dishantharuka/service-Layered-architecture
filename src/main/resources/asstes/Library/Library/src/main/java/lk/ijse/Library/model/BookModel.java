package lk.ijse.Library.model;

import lk.ijse.Library.dto.BookDto;
import lk.ijse.Library.db.DbConnection;
import lk.ijse.Library.dto.SupplierDto;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookModel {


    public boolean saveBook(BookDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO book VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAuthor());
        pstm.setString(4, dto.getCatagory());
        pstm.setString(5, dto.getLanguege());
        pstm.setString(6, dto.getD_id());
        pstm.setString(7, dto.getS_id());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean updateSupplier(final SupplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE book SET Name = ?, Author = ?,Catagory = ?,D_id = ?, tel = ? WHERE Id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAuthor());
        pstm.setString(4, dto.getCatagory());
        pstm.setString(5, dto.getLanguege());
        pstm.setString(6, dto.getD_id());
        pstm.setString(7, dto.getS_id());


        return pstm.executeUpdate() > 0;
    }
}
