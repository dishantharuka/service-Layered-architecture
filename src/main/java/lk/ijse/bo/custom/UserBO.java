package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.Userdto;

import java.sql.SQLException;

public interface UserBO extends SuperBO {

    boolean saveUser(Userdto Userdto) throws Exception;

    Userdto searchUser(String code) throws SQLException;

    boolean deleteUser(String id) throws SQLException;

    boolean updateUser(Userdto dto) throws SQLException;

    String getEmail(String email) throws SQLException;

    boolean isExistUser(String UserName, String Password) throws SQLException;

    String[] loadAllUsers() throws SQLException;
}
