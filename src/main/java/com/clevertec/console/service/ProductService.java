package com.clevertec.console.service;

import com.clevertec.dao.crud.ProductCRUD;
import com.clevertec.entity.Product;
import com.clevertec.entity.User;
import com.clevertec.entity.util.ProductType;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.util.Scanner;

import static lombok.AccessLevel.PRIVATE;

@Log4j
@NoArgsConstructor(access = PRIVATE)
public class ProductService {
    private static final ProductService productService = new ProductService();

    public static ProductService getInstance() {
        return productService;
    }

    private static final ProductCRUD productCRUD = ProductCRUD.getInstance();

    public Product create(Scanner scanner, User user) {
        Product product = new Product();
        log.info("""
                \n
                Set product type:
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
                """.indent(1));
        int i = scanner.nextInt();
        switch (i) {
            case 1 -> product.setProductType(ProductType.MEAT);
            case 2 -> product.setProductType(ProductType.FISH);
            case 3 -> product.setProductType(ProductType.FRUIT);
            case 4 -> product.setProductType(ProductType.VEGETABLE);
            case 5 -> product.setProductType(ProductType.MILK);
            case 6 -> product.setProductType(ProductType.TEA);
            case 7 -> product.setProductType(ProductType.COFFEE);
            case 8 -> product.setProductType(ProductType.SUGAR);
            case 9 -> product.setProductType(ProductType.SALT);
            case 10 -> product.setProductType(ProductType.BUTTER);
            default -> log.info("Type not exist. You should chose among 1..10");
        }
        if (product.getProductType() != null) {
            log.info("Set name:");
            product.setName(scanner.next());
            log.info("Set price:");
            product.setPrice(scanner.nextFloat());
            log.info("Set weight:");
            product.setWeight(scanner.nextFloat());
            log.info("Set id:");
            product.setId(scanner.nextInt());
            productCRUD.create(product, user);
        }
        return product;
    }
    public void printAll() {
        productCRUD.findAll().forEach(log::info);
        log.info("");
    }

    public boolean delete(Scanner scanner) {
        log.info("Enter id of product which you want to delete");
        return productCRUD.delete(scanner.nextInt());
    }

    public int update(Scanner scanner, User user) {
        log.info("Enter id of product which you want to update");
        int id = scanner.nextInt();
        productCRUD.update(id);
        log.info("Create a new product");
        productService.create(scanner, user);
        return id;
    }

    public Product findById(int id) {
        if(productCRUD.findById(id).isPresent()){
            return productCRUD.read(productCRUD.findById(id).get());
        } else log.info("You entered not right id of Product");
        return new Product();
    }

    public static void main(String[] args) {
        Product product = ProductService.getInstance().create(new Scanner(System.in), new User());
    }

}
