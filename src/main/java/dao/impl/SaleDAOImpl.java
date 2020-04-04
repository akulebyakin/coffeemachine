package dao.impl;

import dao.DAO;
import model.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

// id	name	quantity	total_price	paid_by_cash	paid_by_card	date    client_name
public class SaleDAOImpl implements DAO<Sale, Integer> {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public int insert(Sale sale) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("INSERT INTO drinks_sold " +
                    "VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT, ?)");
            ps.setString(1, sale.getName());
            ps.setInt(2, sale.getQuantity());
            ps.setInt(3, sale.getTotalPrice());
            ps.setInt(4, sale.getPaidByCash());
            ps.setInt(5, sale.getPaidByCard());
//            ps.setDate(6, sale.getDate());
            ps.setString(6, sale.getClientName());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting the sale", e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Sale get(Integer key) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("SELECT * FROM drinks_sold WHERE id = " + key);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getSaleFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting the sale", e);
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public Sale getByParameter(String parameter, String value) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("SELECT * FROM drinks_sold WHERE " + parameter + " = ?");
            ps.setString(1, value);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getSaleFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting the sale by parameter " + parameter, e);
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public List<Sale> getAll() {
        List<Sale> list = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("SELECT * FROM drinks_sold");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(getSaleFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all sales", e);
        } finally {
            closeConnection();
        }
        return list;
    }

    @Override
    public int update(Integer key, Sale sale) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("UPDATE drinks_sold SET name=?, quantity=?, total_price=?," +
                    " paid_by_cash=?, paid_by_card=?, date=?, client_name=? WHERE id = " + key);
            ps.setString(1, sale.getName());
            ps.setInt(2, sale.getQuantity());
            ps.setInt(3, sale.getTotalPrice());
            ps.setInt(4, sale.getPaidByCash());
            ps.setInt(5, sale.getPaidByCard());
            ps.setDate(6, sale.getDate());
            ps.setString(7, sale.getClientName());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating the sale", e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public int delete(Integer key) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement("DELETE FROM drinks_sold WHERE id = " + key);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting the sale", e);
        } finally {
            closeConnection();
        }
    }

    private Sale getSaleFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int quantity = rs.getInt("quantity");
        int total_price = rs.getInt("total_price");
        int paid_by_cash = rs.getInt("paid_by_cash");
        int paid_by_card = rs.getInt("paid_by_card");
        Date date = rs.getDate("date");
        String clientName = rs.getString("client_name");

        return new Sale(id, name, quantity, total_price, paid_by_cash, paid_by_card, date, clientName);
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
