package com.clevertec.console;

import com.clevertec.entity.Admin;
import com.clevertec.entity.Discount;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class InitializationManager {
    private final static InitializationManager initializationManager = new InitializationManager();

    public static InitializationManager getInstance(){
        return initializationManager;
    }

    public static void initialize(){
        Admin admin = new Admin();
        admin.setId(UUID.randomUUID());
        admin.setName("Admin");
        admin.setLogin("ADMIN");
        admin.setPassword("1234");

    }
    public Discount initializeConditionsDiscount(Discount discount){
        discount.setPercentPersonalDiscount(10F);
        discount.setPercentTotalDiscount(3);
        discount.setQuantityProduct(5);
        return discount;

    }

}
