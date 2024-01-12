package lk.ijse.dao.Custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.Userdto;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<Userdto> {

    String getEmail(String email) throws SQLException;

    boolean isExistUser(String UserName, String Password) throws SQLException;

    String[] loadAllUsers() throws SQLException;
}
