package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.Custom.Impl.UserDAOImpl;
import lk.ijse.dao.Custom.UserDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.Userdto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    UserDAO userDAO=new UserDAOImpl();
    public  boolean saveUser(Userdto Userdto) throws Exception {

        return userDAO.save(Userdto);
    }

    public  Userdto searchUser(String code) throws SQLException {

            return userDAO.search(code);

        }

    public boolean deleteUser(String id) throws SQLException {

        return userDAO.delete(id);
    }

    public boolean updateUser(Userdto dto) throws SQLException {

        return userDAO.update(dto);
    }

    public  String getEmail(String email) throws SQLException {

        ResultSet resultSet =SQLUtil.execute("SELECT email FROM user WHERE email=?",email);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

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


    public String[] loadAllUsers() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT UserID FROM user");

        String [] userIDs = {};

        while(resultSet.next()){
            userIDs = new String[]{resultSet.getString(1)};
        }
        return userIDs;
    }
}
