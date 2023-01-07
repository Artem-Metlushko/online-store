package com.clevertec.entity;

import com.clevertec.entity.util.ProductType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductInBasket implements Serializable {
    private final int id;
    private final ProductType productType;
    private final String name;
    private final float price;
    private int quantity;
    private float total;
    private final float weight;
    private Product product;

    public ProductInBasket(Product product) {
        this.product = product;
        this.id = product.getId();
        this.productType = product.getProductType();
        this.price = product.getPrice();
        this.name = product.getName();
        this.weight = product.getWeight();
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", productType=" + productType +
                ", name='" + name +
                ", price=" + price +
                ", quantity=" + quantity +
                ", total=" + total +
                ", weight=" + weight;
    }

}