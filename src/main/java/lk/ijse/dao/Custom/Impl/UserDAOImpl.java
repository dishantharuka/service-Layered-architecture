package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.UserDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.Userdto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<Userdto> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Userdto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO user VALUES(?, ?, ?, ?)",
                dto.getUserId(),dto.getName(),dto.getPassword(),dto.getEmail());
    }

    @Override
    public boolean update(Userdto dto) throws SQLException {
        return SQLUtil.execute("UPDATE user SET Username = ? , Password = ? ,Email  = ? WHERE UserID = ?",
                dto.getUserId(),dto.getName(),dto.getPassword(),dto.getEmail());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM user WHERE UserID = ?",id);
    }

    @Override
    public Userdto search(String id) throws SQLException {
        ResultSet resultSet =SQLUtil.execute("SELECT * FROM user WHERE UserID = ?",id);

        Userdto dto = null;

        if(resultSet.next()) {
            dto = new Userdto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return dto;
    }



    @Override
    public String generateNextID() throws SQLException {
        return null;
    }

    @Override
    public String splitID(String currentID) {
        return null;
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }

    @Override
    public  String getEmail(String email) throws SQLException {

        ResultSet resultSet =SQLUtil.execute("SELECT email FROM user WHERE email=?",email);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }


    @Override
    public  boolean isExistUser(String UserName, String Password) throws SQLException {

        ResultSet resultSet =SQLUtil.execute("SELECT Password ,Username ,Email FROM user WHERE Username=? AND Password=?",
                UserName,Password);
        String dbUserName = null;
        String dbPassword = null;
        if (resultSet.next()) {
            dbPassword = resultSet.getString(1);
            dbUserName = resultSet.getString(2);
            DbConnection.Email = resultSet.getString(3);
        }

        return UserName.equals(dbUserName) && Password.equals(dbPassword);
    }

@Override
    public String[] loadAllUsers() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT UserID FROM user");

        String [] userIDs = {};

        while(resultSet.next()){
            userIDs = new String[]{resultSet.getString(1)};
        }
        return userIDs;
    }
}
