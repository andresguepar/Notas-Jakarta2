package com.example.notasjakarta.repository;

import com.example.notasjakarta.services.impl.ServiceJdbcException;

import java.util.List;

public interface Repository<T>{
    List<T> list() throws ServiceJdbcException;

    T byId(Long id);

    void add(T t);

    void delete(Long id);
}
