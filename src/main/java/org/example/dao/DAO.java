package org.example.dao;

import java.util.Collection;

public interface DAO <T> {
    boolean add(T element);
    T get(int index);
    Collection<T> getAll();

}
