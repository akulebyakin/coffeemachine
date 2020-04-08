package dao.impl;

import dao.DAO;
import model.DrinkComposition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//  DrinkCompositions columns: recipe_id, drinkComposition_id, amount
public class DrinkCompositionDAOImpl implements DAO<DrinkComposition, Integer> {
    private Connection connection;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public DrinkCompositionDAOImpl() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public int insert(DrinkComposition drinkComposition) throws SQLException {
        try {
            ps = connection.prepareStatement("INSERT INTO  drink_compositions VALUES (DEFAULT, ?, ?, ?)");
            ps.setInt(1, drinkComposition.getRecipeId());
            ps.setInt(2, drinkComposition.getIngredientId());
            ps.setInt(3, drinkComposition.getAmount());
            return ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
        }
    }

    @Override
    public DrinkComposition get(Integer key) throws SQLException {
        try {
            ps = connection.prepareStatement("SELECT * FROM drink_compositions WHERE id = " + key);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getDrinkCompositionFromResultSet(rs);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return null;
    }

    @Override
    public List<DrinkComposition> getByParameter(String parameter, String value) throws SQLException {
        List<DrinkComposition> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT * FROM drink_compositions WHERE " + parameter + " = ?");
            ps.setString(1, value);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(getDrinkCompositionFromResultSet(rs));
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return list;
    }

    @Override
    public List<DrinkComposition> getAll() throws SQLException {
        List<DrinkComposition> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT * FROM drink_compositions");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(getDrinkCompositionFromResultSet(rs));
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return list;
    }

    @Override
    public int update(Integer key, DrinkComposition drinkComposition) throws SQLException {
        try {
            ps = connection.prepareStatement("UPDATE drink_compositions SET name=?, balance=?, unit=? WHERE id = " + key);
            ps.setInt(1, drinkComposition.getRecipeId());
            ps.setInt(2, drinkComposition.getIngredientId());
            ps.setInt(3, drinkComposition.getAmount());
            return ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
        }
    }

    @Override
    public int delete(Integer key) throws SQLException {
        try {
            ps = connection.prepareStatement("DELETE FROM drink_compositions WHERE id = " + key);
            return ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
        }
    }

    private DrinkComposition getDrinkCompositionFromResultSet(ResultSet rs) throws SQLException {
        int recipeId = rs.getInt("recipe_id");
        int drinkCompositionId = rs.getInt("ingredient_id");
        int amount = rs.getInt("amount");

        return new DrinkComposition(recipeId, drinkCompositionId, amount);
    }

}