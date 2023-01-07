package com.clevertec.console.service;

import com.clevertec.dao.crud.DiscountCRUD;
import com.clevertec.dao.crud.ProductInBasketCRUD;
import com.clevertec.entity.*;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Log4j
@NoArgsConstructor(access = PRIVATE)
public class BasketService {
    private static final BasketService basketService = new BasketService();

    public static BasketService getInstance() {
        return basketService;
    }

    private static final ProductInBasketService productInBasketService = ProductInBasketService.getInstance();
    private static final DiscountService discountService = DiscountService.getInstance();
    private static final ProductInBasketCRUD productInBasketCRUD = ProductInBasketCRUD.getInstance();

    private static final DiscountCRUD discountCRUD = com.clevertec.dao.crud.DiscountCRUD.getInstance();

    public void printCheck(User user) {
        log.info(String.format("--------------------------------------%n"));
        log.info(String.format("          Green Market         %n"));
        log.info(String.format("          Discount Card %3d %n",discountService.getDiscount(user).getNumberCard()));
        log.info(String.format("--------------------------------------%n"));
        log.info(String.format("| %-3s | %-10s | %-6s | %-6s |%n", "QTY", "PRODUCT", "PRICE", "TOTAL"));
        log.info(String.format("--------------------------------------%n"));
        productInBasketCRUD.findAll(user).forEach(
                position -> log.info(String.format("  %3d   %-10s   %-6s   %-6s  %n",
                        position.getQuantity(),
                        position.getProduct().getProductType(),
                        position.getProduct().getPrice(),
                        position.getTotal())));
        log.info(String.format("--------------------------------------%n"));
        if (discountService.getDiscount(user).getNumberCard() != null) {
            log.info(String.format("Total                            %4s %n", getSumWithoutDiscount(user)));
            log.info(String.format("Discount amount                   %4s %n", getSumWithDiscount(user)));
            log.info(String.format("Total                            %4s %n", getSumWithoutDiscount(user) - getSumWithDiscount(user)));
        } else {
            log.info(String.format("AMOUNT FOR PAYMENT              %4s$ %n", getSumWithoutDiscount(user)));
        }

    }

    private float getSumWithDiscount(User user) {
        List<ProductInBasket> list = productInBasketCRUD.findAll(user);
        Discount discount = discountService.getDiscount(user);
        float totalSumWithDiscount = 0;
        for (ProductInBasket productInBasket : list) {
            totalSumWithDiscount += productInBasket.getTotal() * discount.getPercentTotalDiscount() / 100;
        }
        return totalSumWithDiscount;
    }

    private float getSumWithoutDiscount(User user) {
        List<ProductInBasket> list = productInBasketCRUD.findAll(user);
        float totalSumWithoutDiscount = 0;
        for (ProductInBasket productInBasket : list) {
            totalSumWithoutDiscount += productInBasket.getTotal();
        }
        return totalSumWithoutDiscount;
    }

}
