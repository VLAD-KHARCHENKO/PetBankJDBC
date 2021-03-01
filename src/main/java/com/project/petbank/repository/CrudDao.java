package com.project.petbank.repository;

public interface CrudDao<T> {

    T getById(long id);

    T create(T entity);

    boolean update(T entity);

    boolean remove(T entity);

}
