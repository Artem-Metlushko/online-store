package com.clevertec.entity.util;

public enum ProductType {
    MEAT(1),
    FISH(2),
    FRUIT(3),
    VEGETABLE(4),
    MILK(5),
    TEA(6),
    COFFEE(7),
    SUGAR(8),
    SALT(9),
    BUTTER(10);

    private final long id;

    ProductType(long id) {
        this.id = id;
    }

    public long getId(){
        return id;
    }
}
