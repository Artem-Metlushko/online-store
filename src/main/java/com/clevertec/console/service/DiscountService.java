package com.clevertec.console.service;

import com.clevertec.console.InitializationManager;
import com.clevertec.dao.crud.DiscountCRUD;
import com.clevertec.entity.Discount;
import com.clevertec.entity.Product;
import com.clevertec.entity.ProductInBasket;
import com.clevertec.entity.User;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
@Log4j
public class DiscountService {
    private static final DiscountService discountService = new DiscountService();

    public static DiscountService getInstance() {
        return discountService;
    }

    private static final DiscountCRUD discountCRUD = DiscountCRUD.getInstance();
    private static final InitializationManager initializationManager = InitializationManager.getInstance();


    public void create(int numberCard, User user) {

        Discount discount = new Discount();
        discount.setNameUser(user.getName());
        discount.setNumberCard(numberCard);
        Discount iDiscount = initializationManager.initializeConditionsDiscount(discount);
        discountCRUD.create(iDiscount, user);

    }

    public boolean delete(User user) {
        return discountCRUD.delete(user);
    }

    public void print(User user) {
        File file = discountCRUD.findByNumber(user).get();
        if (discountCRUD.findByNumber(user).isPresent()) {
            log.info(discountCRUD.read(file));
        } else {
            log.info("Discount card is not exist");
        }
    }

    public Discount getDiscount(User user){
        File file = discountCRUD.findByNumber(user).get();
        if (discountCRUD.findByNumber(user).isPresent()) {
            return  discountCRUD.read(file);
        } else {
            log.info("Discount card is not exist");
        }
        return new Discount();
    }


    public static void main(String[] args) {
        User user = new User();
        user.setName("Artem");
        new DiscountService().create(123, user);
    }
}
