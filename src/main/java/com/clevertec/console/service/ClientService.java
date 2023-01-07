package com.clevertec.console.service;

import com.clevertec.dao.crud.UserCRUD;
import com.clevertec.entity.Client;
import com.clevertec.entity.User;
import com.clevertec.entity.util.Role;
import lombok.extern.log4j.Log4j;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

@Log4j
public class ClientService {
    private static final ClientService clientService = new ClientService();

    public static ClientService getInstance() {
        return clientService;
    }

    private static final UserCRUD clientCRUD = UserCRUD.getInstance();

    public Client registration(Scanner scanner) {
        Client client = new Client();
        scanner.nextLine();
        client.setRole(Role.CLIENT);
        client.setId(UUID.randomUUID());
        log.info("Registration");
        log.info("Name :");
        client.setName(scanner.nextLine());
        log.info("Login :");
        client.setLogin(scanner.nextLine());
        log.info("Password :");
        client.setPassword(scanner.nextLine());
        log.info("Email :");
        client.setEmailAddress(scanner.nextLine());
        clientCRUD.create(client);
        return client;
    }

    public Client read(User user) {
        return clientCRUD.findByID(user).isPresent() ?
                (Client)clientCRUD.findByID(user).get() :
                new Client();

    }

    public void print(User user) {
        Client client = read(user);
        log.info(read(user));
    }


    public Optional<Client> verification(Scanner scanner) {
        scanner=new Scanner(System.in);
        log.info("Sign in");
        log.info("Login :");
        String login = scanner.nextLine();
        log.info("Password");
        String password = scanner.nextLine();
        File file = Path.of("save", "user", login + ".txt").toFile();
        Client user = new Client();
        try {
            user = (Client) clientCRUD.read(file);
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                return Optional.of(user);
            }
        } catch (ClassCastException|NullPointerException e) {
            log.info("Please enter right data");
        }
        return Optional.of(user);
    }


}
