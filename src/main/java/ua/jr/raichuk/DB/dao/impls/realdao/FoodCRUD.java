package ua.jr.raichuk.DB.dao.impls.realdao;


import ua.jr.raichuk.DB.dao.impls.CCRUD;
import ua.jr.raichuk.DB.entities.impls.Food;
import ua.jr.raichuk.Exceptions.DBException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jesus Raichuk
 */
public class FoodCRUD extends CCRUD<Food> {
    FoodCRUD(){}

    @Override
    protected String getTableName() {
        return "foods";
    }

    @Override
    protected String getTableIdRowName() {
        return "idF";
    }

    @Override
    protected List<String> getTableRowsNames() {
        List rows = new ArrayList<String>();
        rows.add("idF");
        rows.add("name");
        rows.add("calories");
        rows.add("proteins");
        rows.add("fats");
        rows.add("carbohydrates");
        return rows;
    }

    @Override
    protected void parseResultSetGeneratedKeys(ResultSet generatedKeys, Food entity) throws DBException{
        try {
            if (generatedKeys.next()) {
                entity.setIdF(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    protected PreparedStatement getFullIdValuePreparedStatement(PreparedStatement preparedStatement, Food food, int from) throws SQLException {
        PreparedStatement ps = preparedStatement;
        ps.setInt(from, food.getIdF());
        return ps;
    }

    @Override
    protected PreparedStatement getFullSecondaryValuesPreparedStatement(PreparedStatement preparedStatement, Food food, int from) throws SQLException {
        PreparedStatement ps = preparedStatement;
        ps.setString(from++, food.getName());
        ps.setDouble(from++, food.getCalories());
        ps.setDouble(from++, food.getProteins());
        ps.setDouble(from++, food.getFats());
        ps.setDouble(from, food.getCarbohydrates());
        return ps;
    }

    @Override
    protected Food fillEntity(ResultSet resultSet) throws SQLException {
        Food food = new Food();
        food.setIdF(resultSet.getInt("idF"));
        food.setName(resultSet.getString("name"));
        food.setCalories(resultSet.getDouble("calories"));
        food.setProteins(resultSet.getDouble("proteins"));
        food.setFats(resultSet.getDouble("fats"));
        food.setCarbohydrates(resultSet.getDouble("carbohydrates"));
        return food;
    }
}
