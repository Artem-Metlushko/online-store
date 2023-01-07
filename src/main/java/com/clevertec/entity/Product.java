package com.clevertec.entity;


import com.clevertec.entity.util.ProductType;
import lombok.*;

import java.io.Serializable;

@Data
public class Product implements Serializable {
    private int id;
    private ProductType productType;
    private String name;
    private float price;
    private float weight;

}

