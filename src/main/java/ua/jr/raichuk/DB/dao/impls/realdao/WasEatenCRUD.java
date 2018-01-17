package ua.jr.raichuk.DB.dao.impls.realdao;


import ua.jr.raichuk.DB.dao.impls.CCRUD;
import ua.jr.raichuk.DB.entities.impls.WasEaten;
import ua.jr.raichuk.Exceptions.DBException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jesus Raichuk
 */
public class WasEatenCRUD extends CCRUD<WasEaten> {
    WasEatenCRUD(){}
    @Override
    protected String getTableName() {
        return "wasEaten";
    }

    @Override
    protected String getTableIdRowName() {
        return "idWE";
    }

    @Override
    protected List<String> getTableRowsNames() {
        List rows = new ArrayList<String>();
        rows.add("idWE");
        rows.add("idP");
        rows.add("idF");
        rows.add("dateWE");
        return rows;
    }

    @Override
    protected PreparedStatement getFullIdValuePreparedStatement(PreparedStatement preparedStatement, WasEaten wasEaten, int from) throws SQLException {
        PreparedStatement ps = preparedStatement;
        ps.setInt(from, wasEaten.getIdWE());
        return ps;
    }

    @Override
    protected PreparedStatement getFullSecondaryValuesPreparedStatement(PreparedStatement preparedStatement, WasEaten wasEaten, int from) throws SQLException {
        PreparedStatement ps = preparedStatement;
        ps.setInt(from++, wasEaten.getIdP());
        ps.setInt(from++, wasEaten.getIdF());
        ps.setDate(from, wasEaten.getDateWE());
        return ps;
    }

    @Override
    protected WasEaten fillEntity(ResultSet resultSet) throws SQLException {
        WasEaten wasEaten = new WasEaten();
        wasEaten.setIdWE(resultSet.getInt("idWE"));
        wasEaten.setIdP(resultSet.getInt("idP"));
        wasEaten.setIdF(resultSet.getInt("idF"));
        wasEaten.setDateWE(resultSet.getDate("dateWE"));
        return wasEaten;
    }
    @Override
    protected void parseResultSetGeneratedKeys(ResultSet generatedKeys, WasEaten entity) throws DBException {
        try {
            if (generatedKeys.next()) {
                entity.setIdWE(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

}
