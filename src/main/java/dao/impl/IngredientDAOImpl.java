package dao.impl;

import dao.DAO;
import model.Ingredient;

import java.sql.*;
import java.util.List;

public class IngredientDAOImpl implements DAO<Ingredient, Integer> {
    private Connection connection;
    private Statement statement;
    private PreparedStatement ps;
    private ResultSet rs;

    public IngredientDAOImpl() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(Ingredient ingredient) {
        try {
            ps = connection.prepareStatement("INSERT INTO  ingredients VALUES (DEFAULT, ?, ?, ?)");
            ps.setString(1, ingredient.getName());
            ps.setInt(2, ingredient.getBalance());
            ps.setString(3, ingredient.getUnit());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting the ingredient", e);
        } finally {
            closeConnection();
        }

    }

    @Override
    public Ingredient get(Integer key) {
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM ingredients WHERE id = " + key);
            if (rs.next()) {
                return getIngredientFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting the ingredient", e);
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public List<Ingredient> getAll() {
        // TODO Make getAll method for Ingredient
        return null;
    }

    @Override
    public void update(Integer key) {
        // TODO Make update method for Ingredient
    }

    @Override
    public void delete(Integer key) {
        // TODO Make delete method for Ingredient
    }

    private Ingredient getIngredientFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int balance = rs.getInt("balance");
        String unit = rs.getString("unit");

        return new Ingredient(id, name, balance, unit);
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error closing connection", e);
        }
        try {
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error closing prepared statement", e);
        }
        try {
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error closing statement", e);
        }
        try {
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error closing result set", e);
        }
    }
}
