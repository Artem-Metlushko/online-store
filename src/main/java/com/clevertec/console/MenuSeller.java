package com.clevertec.console;

import com.clevertec.console.service.ProductService;
import com.clevertec.entity.Discount;
import com.clevertec.entity.Seller;
import com.clevertec.entity.User;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

import java.util.Scanner;

import static lombok.AccessLevel.PRIVATE;

@Log4j
@ToString
@NoArgsConstructor(access = PRIVATE)
public class MenuSeller {
    private static final MenuSeller menuSeller = new MenuSeller();
    private static final ProductService productService = ProductService.getInstance();
    public static MenuSeller getInstance() {
        return menuSeller;
    }
    public void showSellerMenu(Scanner scanner, User seller) {
        int s;
        do {
            log.info("""
                    \n
                    Choose (1) add product to catalog
                    Choose (2) show catalog of products
                    Choose (3) remove product
                    Choose (4) update product
                    Choose (5) exit
                    """.indent(1));
            s = scanner.nextInt();
            switch (s) {
                case 1 -> productService.create(scanner,seller);
                case 2 -> productService.printAll();
                case 3 -> productService.delete(scanner);
                case 4 -> productService.update(scanner,seller);
            }
        } while (!(s == 5));
    }

    public static void main(String[] args) {
//        SellerService sellerService1 = SellerService.getInstance();
        menuSeller.showSellerMenu(new Scanner(System.in), new Seller());

    }
}
