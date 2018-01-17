package ua.jr.raichuk.DB.dao.impls.realdao;

import ua.jr.raichuk.DB.dao.impls.CCRUD;
import ua.jr.raichuk.DB.entities.impls.Norm;
import ua.jr.raichuk.Exceptions.DBException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jesus Raichuk
 */
public class NormCRUD extends CCRUD<Norm> {
    NormCRUD(){}

    @Override
    protected String getTableName() {
        return "norms";
    }

    @Override
    protected String getTableIdRowName() {
        return "idN";
    }

    @Override
    protected List<String> getTableRowsNames() {
        List rows = new ArrayList<String>();
        rows.add("idN");
        rows.add("calories");
        rows.add("proteins");
        return rows;
    }

    @Override
    protected PreparedStatement getFullIdValuePreparedStatement(PreparedStatement preparedStatement, Norm norm, int from) throws SQLException {
        PreparedStatement ps = preparedStatement;
        ps.setInt(from, norm.getIdN());
        return ps;
    }

    @Override
    protected PreparedStatement getFullSecondaryValuesPreparedStatement(PreparedStatement preparedStatement, Norm norm, int from) throws SQLException {
        PreparedStatement ps = preparedStatement;
        ps.setInt(from++, norm.getCalories());
        ps.setInt(from, norm.getProteins());
        return ps;
    }

    @Override
    protected Norm fillEntity(ResultSet resultSet) throws SQLException {
        Norm norm = new Norm();
        norm.setIdN(resultSet.getInt("idN"));
        norm.setCalories(resultSet.getInt("calories"));
        norm.setProteins(resultSet.getInt("proteins"));
        return norm;
    }
    @Override
    protected void parseResultSetGeneratedKeys(ResultSet generatedKeys, Norm entity) throws DBException {
        try {
            if (generatedKeys.next()) {
                entity.setIdN(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

}
