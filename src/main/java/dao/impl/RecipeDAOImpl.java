package dao.impl;

import dao.DAO;
import model.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//id	name	ingredient_ids	ingredient_amount	price	available	total_sold
public class RecipeDAOImpl implements DAO<Recipe, Integer> {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public void insert(Recipe recipe) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("INSERT INTO recipes " +
                    "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, recipe.getName());
            ps.setString(2, recipe.getIngredient_ids());
            ps.setString(3, recipe.getIngredient_amount());
            ps.setInt(4, recipe.getPrice());
            ps.setBoolean(5, recipe.isAvailable());
            ps.setInt(6, recipe.getTotalSold());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting the recipe", e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Recipe get(Integer key) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("SELECT * FROM recipes WHERE id = " + key);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getRecipeFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting the recipe", e);
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public Recipe getByParameter(String parameter, String value) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("SELECT * FROM recipes WHERE " + parameter + " = " + value);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getRecipeFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting the recipe by parameter " + parameter, e);
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public List<Recipe> getAll() {
        List<Recipe> list = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("SELECT * FROM recipes");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(getRecipeFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all recipes", e);
        } finally {
            closeConnection();
        }
        return list;
    }

    @Override
    public void update(Integer key, Recipe recipe) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("UPDATE recipes SET name=?, ingredient_ids=?, ingredient_amount=?," +
                    " price=?, available=?, total_sold=? WHERE id = " + key);
            ps.setString(1, recipe.getName());
            ps.setString(2, recipe.getIngredient_ids());
            ps.setString(3, recipe.getIngredient_amount());
            ps.setInt(4, recipe.getPrice());
            ps.setBoolean(5, recipe.isAvailable());
            ps.setInt(6, recipe.getTotalSold());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating the recipe", e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void delete(Integer key) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("DELETE FROM recipes WHERE id = " + key);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting the ingredient", e);
        } finally {
            closeConnection();
        }
    }

    private Recipe getRecipeFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String ingredient_ids = rs.getString("ingredient_ids");
        String ingredient_amount = rs.getString("ingredient_amount");
        int price = rs.getInt("price");
        boolean available = rs.getBoolean("available");
        int total_sold = rs.getInt("total_sold");

        return new Recipe(id, name, ingredient_ids, ingredient_amount, price, available, total_sold);
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
