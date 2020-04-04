package dao;

import java.util.List;

public interface DAO<T, K> {

    int insert(T t);
    T get(K key);
    T getByParameter(String parameter, String value);
    List<T> getAll();
    int update(K key, T t);
    int delete(K key);

}
