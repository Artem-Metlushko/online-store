package com.clevertec.dao.crud;

import com.clevertec.entity.Product;
import com.clevertec.entity.User;

import java.io.File;

public interface CRUD<T> {
    void create(T t, User user);

    T read(File file);

    boolean update(int id);

    boolean delete(int id);




}
