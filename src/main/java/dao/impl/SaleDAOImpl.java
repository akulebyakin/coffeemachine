package dao.impl;

import dao.DAO;
import model.Sale;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// id	name	quantity	total_price	paid_by_cash	paid_by_card	date    client_name
public class SaleDAOImpl implements DAO<Sale, Integer> {
    private Connection connection;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public SaleDAOImpl() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public int insert(Sale sale) throws SQLException {
        try {
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
        } finally {
            if (ps != null) ps.close();
        }
    }

    @Override
    public Sale get(Integer key) throws SQLException {
        try {
            ps = connection.prepareStatement("SELECT * FROM drinks_sold WHERE id = " + key);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getSaleFromResultSet(rs);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return null;
    }

    @Override
    public List<Sale> getByParameter(String parameter, String value) throws SQLException {
        List<Sale> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT * FROM drinks_sold WHERE " + parameter + " = ?");
            ps.setString(1, value);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(getSaleFromResultSet(rs));
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return list;
    }

    @Override
    public List<Sale> getAll() throws SQLException {
        List<Sale> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT * FROM drinks_sold");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(getSaleFromResultSet(rs));
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return list;
    }

    @Override
    public int update(Integer key, Sale sale) throws SQLException {
        try {
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
        } finally {
            if (ps != null) ps.close();
        }
    }

    @Override
    public int delete(Integer key) throws SQLException {
        try {
            ps = connection.prepareStatement("DELETE FROM drinks_sold WHERE id = " + key);
            return ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
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
}
