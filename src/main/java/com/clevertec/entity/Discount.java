package com.clevertec.entity;
import lombok.Data;

import java.io.Serializable;

@Data
public class Discount implements Serializable {
    private String nameUser;
    private Integer numberCard;
    private int quantityProduct;
    private float percentTotalDiscount;
    private float percentPersonalDiscount;
}
