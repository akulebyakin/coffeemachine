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

    public RecipeDAOImpl() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public int insert(Recipe recipe) throws SQLException {
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
            return ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
        }
    }

    @Override
    public Recipe get(Integer key) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("SELECT * FROM recipes WHERE id = " + key);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getRecipeFromResultSet(rs);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return null;
    }

    @Override
    public List<Recipe> getByParameter(String parameter, String value) throws SQLException {
        List<Recipe> list = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("SELECT * FROM recipes WHERE " + parameter + " = ?");
            ps.setString(1, value);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(getRecipeFromResultSet(rs));
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return list;
    }

    @Override
    public List<Recipe> getAll() throws SQLException {
        List<Recipe> list = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("SELECT * FROM recipes");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(getRecipeFromResultSet(rs));
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return list;
    }

    @Override
    public int update(Integer key, Recipe recipe) throws SQLException {
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
            return ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
        }
    }

    @Override
    public int delete(Integer key) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("DELETE FROM recipes WHERE id = " + key);
            return ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
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

}
