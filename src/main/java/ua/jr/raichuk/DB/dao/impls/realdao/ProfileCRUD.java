package ua.jr.raichuk.DB.dao.impls.realdao;


import ua.jr.raichuk.DB.dao.impls.CCRUD;
import ua.jr.raichuk.DB.entities.impls.Profile;
import ua.jr.raichuk.Exceptions.DBException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jesus Raichuk
 */
public class ProfileCRUD extends CCRUD<Profile> {
    ProfileCRUD(){}
    @Override
    protected String getTableName() {
        return "profiles";
    }

    @Override
    protected String getTableIdRowName() {
        return "idP";
    }

    @Override
    protected List<String> getTableRowsNames() {
        List rows = new ArrayList<String>();
        rows.add("idP");
        rows.add("name");
        rows.add("surname");
        rows.add("sex");
        rows.add("birthday");
        rows.add("height");
        rows.add("weight");
        rows.add("activeTime");
        return rows;
    }

    @Override
    protected PreparedStatement getFullIdValuePreparedStatement(PreparedStatement preparedStatement, Profile profile, int from) throws SQLException {
        PreparedStatement ps = preparedStatement;
        ps.setInt(from, profile.getIdP());
        return ps;
    }

    @Override
    protected PreparedStatement getFullSecondaryValuesPreparedStatement(PreparedStatement preparedStatement, Profile profile, int from) throws SQLException {
        PreparedStatement ps = preparedStatement;
        ps.setString(from++, profile.getName());
        ps.setString(from++, profile.getSurname());
        ps.setString(from++, profile.getSex());
        ps.setDate(from++, profile.getBirthday());
        ps.setDouble(from++, profile.getHeight());
        ps.setDouble(from++, profile.getWeight());
        ps.setInt(from, profile.getActiveTime());
        return ps;
    }

    @Override
    protected Profile fillEntity(ResultSet resultSet) throws SQLException {
        Profile profile = new Profile();
        profile.setIdP(resultSet.getInt("idP"));
        profile.setName(resultSet.getString("name"));
        profile.setSurname(resultSet.getString("surname"));
        profile.setSex(resultSet.getString("sex"));
        profile.setBirthday(resultSet.getDate("birthday"));
        profile.setHeight(resultSet.getDouble("height"));
        profile.setWeight(resultSet.getDouble("weight"));
        profile.setActiveTime(resultSet.getInt("activeTime"));
        return profile;
    }

    @Override
    protected void parseResultSetGeneratedKeys(ResultSet generatedKeys, Profile entity) throws DBException {
        try {
            if (generatedKeys.next()) {
                entity.setIdP(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }
}
