package com.clevertec.console;

import com.clevertec.console.service.ClientService;
import com.clevertec.entity.Client;
import com.clevertec.entity.Seller;
import com.clevertec.entity.User;
import com.clevertec.entity.util.Role;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.util.Scanner;

import static lombok.AccessLevel.PRIVATE;

@Log4j
@NoArgsConstructor(access = PRIVATE)
public class MenuMain {
    private static final MenuMain menuMain = new MenuMain();
    public static MenuMain getInstance() {
        return menuMain;
    }
    private static final MenuClient menuClient = MenuClient.getInstance();
    private static final MenuSeller menuSeller = MenuSeller.getInstance();

    private static final ClientService clientService = ClientService.getInstance();

    public static void showMainMenu() {
        log.info("""
                Choose (1) for registration 
                Choose (2) for sign in
                """.indent(1));
        Scanner scanner = new Scanner(System.in);
        String firstRequest;
        do {
            firstRequest = scanner.next();
            switch (firstRequest) {
                case "1" -> {
                    log.info("""
                            \n
                            Choose (1) if you client
                            Choose (2) if you seller
                            """.indent(1));
                    int secondRequest = scanner.nextInt();
                    switch (secondRequest) {
                        case 1 -> {
                            User user = clientService.registration(scanner);
                            menuClient.showClientMenu(scanner, user);
                        }
                        case 2 -> {
//
                            User seller = new Seller();
                            menuSeller.showSellerMenu(scanner, seller);
                        }
                    }
                }
                case "2" -> {

                        if(clientService.verification(scanner).isPresent()) {
                            Client user = clientService.verification(scanner).get();
                            if (Role.CLIENT.equals(user.getRole())) {
                                menuClient.showClientMenu(scanner, user);
                            } else if (Role.SELLER.equals(user.getRole())) {
                                menuSeller.showSellerMenu(scanner, user);
                            }
                        }


                }
                default -> log.info("Please enter (1) or (2)");
            }
        } while (!("1".equals(firstRequest) || "2".equals(firstRequest)));
    }







}
