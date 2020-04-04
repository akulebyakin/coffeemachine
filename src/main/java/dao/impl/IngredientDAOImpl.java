package dao.impl;

import dao.DAO;
import model.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//  Ingredients columns: id, name, balance, unit
public class IngredientDAOImpl implements DAO<Ingredient, Integer> {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

//    public IngredientDAOImpl() throws SQLException {
//        connection = ConnectionFactory.getConnection();
//    }

    @Override
    public int insert(Ingredient ingredient) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("INSERT INTO  ingredients VALUES (DEFAULT, ?, ?, ?)");
            ps.setString(1, ingredient.getName());
            ps.setInt(2, ingredient.getBalance());
            ps.setString(3, ingredient.getUnit());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting the ingredient", e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Ingredient get(Integer key) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("SELECT * FROM ingredients WHERE id = " + key);
            rs = ps.executeQuery();
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
    public Ingredient getByParameter(String parameter, String value) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("SELECT * FROM ingredients WHERE " + parameter + " = ?");
            ps.setString(1, value);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getIngredientFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting the ingredient by parameter " + parameter, e);
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public List<Ingredient> getAll() {
        List<Ingredient> list = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("SELECT * FROM ingredients");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(getIngredientFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all ingredients", e);
        } finally {
            closeConnection();
        }
        return list;
    }

    @Override
    public int update(Integer key, Ingredient ingredient) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("UPDATE ingredients SET name=?, balance=?, unit=? WHERE id = " + key);
            ps.setString(1, ingredient.getName());
            ps.setInt(2, ingredient.getBalance());
            ps.setString(3, ingredient.getUnit());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating the ingredient", e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public int delete(Integer key) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("DELETE FROM ingredients WHERE id = " + key);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting the ingredient", e);
        } finally {
            closeConnection();
        }
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
            if (rs != null) rs.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error closing result set", e);
        }
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error closing prepared statement", e);
        }
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error closing connection", e);
        }
    }
}