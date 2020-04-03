package dao;

import java.util.List;

public interface DAO<T, K> {

    void insert(T t);
    T get(K key);
    List<T> getAll();
    void update(K key);
    void delete(K key);

}