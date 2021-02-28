package com.project.petbank.repository;

import java.util.List;

public interface GetAllDao<T> {

    List<T> getAll();

    List<T> getAllByFieldId(long id);

    List<T> getAllByField(String field);

    List<T> getAllPaginated(int page, int size, String sort, String direction);

    List<T> getAllPaginated(long accountId, int page, int size, String sort, String direction);

}
