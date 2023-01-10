package com.clevertec.console.service;

import com.clevertec.dao.crud.UserCRUD;
import com.clevertec.entity.Client;
import com.clevertec.entity.Seller;
import com.clevertec.entity.User;
import com.clevertec.entity.util.Role;
import lombok.extern.log4j.Log4j;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

@Log4j
public class UserService {
    private static final UserService userService = new UserService();

    public static UserService getInstance() {
        return userService;
    }

    private static final UserCRUD userCRUD = UserCRUD.getInstance();

    public User registration(Scanner scanner, User user) {
        scanner.nextLine();
        user = user.getRole().getRole() == 3 ?
                new Client() : new Seller();
        user.setId(UUID.randomUUID());
        log.info("Registration");
        log.info("Name :");
        user.setName(scanner.nextLine());
        log.info("Login :");
        user.setLogin(scanner.nextLine());
        log.info("Password :");
        user.setPassword(scanner.nextLine());
        log.info("Email :");
        user.setEmailAddress(scanner.nextLine());
        userCRUD.create(user);
        return user;
    }

    public User read(User user) {
        return userCRUD.findByID(user).isPresent() ?
                userCRUD.findByID(user).get() :
                new User();

    }

    public void print(User user) {
        log.info(read(user));
    }


    public Optional<User> verification(Scanner scanner) {
        scanner = new Scanner(System.in);
        log.info("Sign in");
        log.info("Login :");
        String login = scanner.nextLine();
        log.info("Password");
        String password = scanner.nextLine();
        File file = Path.of("save", "user", login + ".txt").toFile();
        User user = new User();
        try {
            user = userCRUD.read(file);
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                return Optional.of(user);
            }
        } catch (ClassCastException | NullPointerException e) {
            log.info("Please enter right data");
        }
        return Optional.of(user);
    }


}
