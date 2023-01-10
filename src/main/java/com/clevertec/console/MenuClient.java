package com.clevertec.console;

import com.clevertec.console.service.*;
import com.clevertec.entity.*;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.util.Scanner;

import static lombok.AccessLevel.PRIVATE;

@Log4j
@NoArgsConstructor(access = PRIVATE)
public class MenuClient {
    private static final MenuClient menuClient = new MenuClient();

    public static MenuClient getInstance() {
        return menuClient;
    }

    private static final ProductInBasketService productInBasketService = ProductInBasketService.getInstance();
    private static final ProductService productService = ProductService.getInstance();
    private static final BasketService basketService = BasketService.getInstance();
    private static final UserService clientService = UserService.getInstance();

    private static final DiscountService discountService = DiscountService.getInstance();
    public void showClientMenu(Scanner scanner, User user) {
        int i;
        do {
            log.info("""
                    Choose (0) exit 
                    Choose (1) add products to basket of products and card
                    Choose (2) remove product with the basket
                    Choose (3) remove discount card 
                    Choose (4) show list of products in the basket
                    Choose (5) show the final cheque
                    Choose (6) show the discount card
                    Choose (7) show catalog product in mall 
                    Choose (8) show the client of name and others properties
                    """.indent(0));
            i = scanner.nextInt();
            switch (i) {
                case 1 -> productInBasketService.create(scanner, user);
                case 2 -> productInBasketService.delete(scanner, user);
                case 3 -> discountService.delete(user);
                case 4 -> productInBasketService.printAll(user);
                case 5 -> basketService.printCheck(user);
                case 6 -> discountService.print(user);
                case 7 -> productService.printAll();
                case 8 -> clientService.print(user);
            }
        } while (!(0 == i));

    }

    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User client = new Client();
        client.setName("artem");
        client.setLogin("qwerty");
        client.setRole(Role.CLIENT);
        menuClient.showClientMenu(scanner, client);
    }*/

}
