package com.clevertec.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Basket implements Serializable {

    private List<ProductInBasket> productsInBasket = new ArrayList<>();
    private Discount discount;
    private float totalSumWithDiscount;
    private float totalSumWithoutDiscount;
}
