package ua.jr.raichuk.DB.dao.impls.realdao;


import ua.jr.raichuk.DB.dao.impls.CCRUD;
import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.Exceptions.DBException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jesus Raichuk
 */
public class UserCRUD extends CCRUD<User> {
    UserCRUD(){}

    @Override
    protected String getTableName(){
        return "users";
    }
    @Override
    protected String getTableIdRowName(){
        return "idU";
    }
    @Override
    protected List<String> getTableRowsNames(){
        List rows = new ArrayList<String>();
        rows.add("idU");
        rows.add("login");
        rows.add("password");
        return rows;
    }

    @Override
    protected PreparedStatement getFullIdValuePreparedStatement(PreparedStatement preparedStatement, User user, int from) throws SQLException {
        PreparedStatement ps = preparedStatement;
        ps.setInt(from, user.getIdU());
        return ps;
    }

    @Override
    protected PreparedStatement getFullSecondaryValuesPreparedStatement(PreparedStatement preparedStatement, User user, int from) throws SQLException {
        PreparedStatement ps = preparedStatement;
        ps.setString(from++, user.getLogin());
        ps.setString(from, user.getPassword());
        return ps;
    }

    @Override
    protected User fillEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setIdU(resultSet.getInt("idU"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

    @Override
    protected void parseResultSetGeneratedKeys(ResultSet generatedKeys, User entity) throws DBException {
        try {
            if (generatedKeys.next()) {
                entity.setIdU(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }
}
