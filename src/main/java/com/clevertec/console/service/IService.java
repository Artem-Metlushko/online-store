package com.clevertec.console.service;

import com.clevertec.entity.User;

import java.util.List;
import java.util.Scanner;

public interface IService<T> {
    T create(Scanner scanner, User user);
    void printAll();
    int delete(Scanner scanner);
    int update(Scanner scanner,User user);

}
