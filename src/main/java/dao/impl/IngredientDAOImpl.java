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
    private Connection connection;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public IngredientDAOImpl() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public int insert(Ingredient ingredient) throws SQLException {
        try {
            ps = connection.prepareStatement("INSERT INTO  ingredients VALUES (DEFAULT, ?, ?, ?)");
            ps.setString(1, ingredient.getName());
            ps.setInt(2, ingredient.getBalance());
            ps.setString(3, ingredient.getUnit());
            return ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
        }
    }

    @Override
    public Ingredient get(Integer key) throws SQLException {
        try {
            ps = connection.prepareStatement("SELECT * FROM ingredients WHERE id = " + key);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getIngredientFromResultSet(rs);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return null;
    }

    @Override
    public List<Ingredient> getByParameter(String parameter, String value) throws SQLException {
        List<Ingredient> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT * FROM ingredients WHERE " + parameter + " = ?");
            ps.setString(1, value);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(getIngredientFromResultSet(rs));
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return list;
    }

    @Override
    public List<Ingredient> getAll() throws SQLException {
        List<Ingredient> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT * FROM ingredients");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(getIngredientFromResultSet(rs));
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return list;
    }

    @Override
    public int update(Integer key, Ingredient ingredient) throws SQLException {
        try {
            ps = connection.prepareStatement("UPDATE ingredients SET name=?, balance=?, unit=? WHERE id = " + key);
            ps.setString(1, ingredient.getName());
            ps.setInt(2, ingredient.getBalance());
            ps.setString(3, ingredient.getUnit());
            return ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
        }
    }

    @Override
    public int delete(Integer key) throws SQLException {
        try {
            ps = connection.prepareStatement("DELETE FROM ingredients WHERE id = " + key);
            return ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
        }
    }

    private Ingredient getIngredientFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int balance = rs.getInt("balance");
        String unit = rs.getString("unit");

        return new Ingredient(id, name, balance, unit);
    }

}