package dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T, K> {

    int insert(T t) throws SQLException;
    T get(K key) throws SQLException;
    List<T> getByParameter(String parameter, String value) throws SQLException;
    List<T> getAll() throws SQLException;
    int update(K key, T t) throws SQLException;
    int delete(K key) throws SQLException;

}
