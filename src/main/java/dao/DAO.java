package dao;

import java.util.List;

public interface DAO<T, K> {

    void insert(T t);
    T get(K key);
    T getByParameter(String parameter, String value);
    List<T> getAll();
    void update(K key, T t);
    void delete(K key);

}
