package ua.jr.raichuk.DB.dao.impls.realdao;

import ua.jr.raichuk.DB.dao.impls.CCRUD;
import ua.jr.raichuk.DB.entities.impls.Link;
import ua.jr.raichuk.Exceptions.DBException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jesus Raichuk
 */
public class LinkCRUD extends CCRUD<Link> {
    LinkCRUD(){}

    @Override
    public String getTableName() {
        return "links";
    }

    @Override
    public String getTableIdRowName() {
        return "idL";
    }

    @Override
    public List<String> getTableRowsNames() {
        List rows = new ArrayList<String>();
        rows.add("idL");
        rows.add("idU");
        rows.add("idP");
        rows.add("idN");
        return rows;
    }

    @Override
    protected PreparedStatement getFullIdValuePreparedStatement(PreparedStatement preparedStatement, Link link, int from) throws SQLException {
        PreparedStatement ps = preparedStatement;
        ps.setInt(from, link.getIdL());
        return ps;
    }

    @Override
    protected PreparedStatement getFullSecondaryValuesPreparedStatement(PreparedStatement preparedStatement, Link link, int from) throws SQLException {
        PreparedStatement ps = preparedStatement;
        ps.setInt(from++, link.getIdU());
        ps.setInt(from++, link.getIdP());
        ps.setInt(from, link.getIdN());
        return ps;
    }

    @Override
    public Link fillEntity(ResultSet resultSet) throws SQLException {
        Link link = new Link();
        link.setIdL(resultSet.getInt("idL"));
        link.setIdU(resultSet.getInt("idU"));
        link.setIdP(resultSet.getInt("idP"));
        link.setIdN(resultSet.getInt("idN"));
        return link;
    }

    @Override
    protected void parseResultSetGeneratedKeys(ResultSet generatedKeys, Link entity) throws DBException {
        try {
            if (generatedKeys.next()) {
                entity.setIdL(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }
}
