package com.clevertec.console.service;

import com.clevertec.dao.crud.ProductInBasketCRUD;
import com.clevertec.entity.*;
import com.clevertec.entity.Client;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.io.File;
import java.util.*;

import static lombok.AccessLevel.PRIVATE;

@Log4j
@NoArgsConstructor(access = PRIVATE)
public class ProductInBasketService {
    private static final ProductInBasketService productInBasketService = new ProductInBasketService();

    public static ProductInBasketService getInstance() {
        return productInBasketService;
    }

    private static final ProductService productService = ProductService.getInstance();
    private static final DiscountService discountService = DiscountService.getInstance();
    private static final ProductInBasketCRUD productInBasketCRUD = ProductInBasketCRUD.getInstance();

    public void create(Scanner scanner, User user) {
        log.info("Enter id of product, quantity product and number of card if you have");
        log.info("Example: 2-2 3-4 6-2 card-1234");
        scanner.nextLine();
        String line = scanner.nextLine();
        List<ProductInBasket> mapToList = getListProductInBasket(parse(line, user));
        mapToList.forEach(productInBasket -> {
            deleteMatch(productInBasket, user);
            productInBasketCRUD.create(productInBasket, user);
        });

    }

    public void printAll(User user) {
        if (productInBasketCRUD.findAll(user).size() == 0) {
            log.info("List is empty");
        } else {
            productInBasketCRUD.findAll(user).forEach(log::info);
        }
    }

    public boolean delete(Scanner scanner, User user) {
        log.info("Enter id of product which you want to remove from basket");
        return productInBasketCRUD.delete(scanner.nextInt(), user);
    }

    public ProductInBasket findById(int id, User user) {
        Optional<File> byId = productInBasketCRUD.findById(id, user);
        if (productInBasketCRUD.findById(id, user).isPresent()) {
            return productInBasketCRUD.read(productInBasketCRUD.findById(id, user).get());
        } else log.info("You entered not right id ");

        return new ProductInBasket(new Product());
    }



    private void deleteMatch(ProductInBasket productInBasket, User user) {
        try {
            Optional<File> fileById = productInBasketCRUD.findById(productInBasket.getId(), user);
            ProductInBasket product = null;
            if (fileById.isPresent()) {
                product = productInBasketCRUD.read(fileById.get());
            }
            if (Objects.requireNonNull(product).getProductType() == productInBasket.getProductType() &&
                    product.getName().equals(productInBasket.getName())
            ) {
                fileById.get().delete();
            }
        } catch (NullPointerException e) {
            log.error("You tru to add product which id isn`t exist");
        }
    }

    private List<ProductInBasket> getListProductInBasket(Map<Integer, Integer> map) {
        List<ProductInBasket> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            Product product = productService.findById(m.getKey());
            if (product.getName() != null) {
                ProductInBasket productInBasket = new ProductInBasket(Objects.requireNonNull(product));
                productInBasket.setQuantity(m.getValue());
                productInBasket.setTotal(productInBasket.getQuantity() * productInBasket.getPrice());
                list.add(productInBasket);
            } else log.info("You entered not right id");
        }
        return list;
    }

    private Map<Integer, Integer> parse(String line, User user) {
        Map<Integer, Integer> map = new HashMap<>();
        String[] args = line.split(" ");
        try {
            Arrays.stream(args).forEach(s -> {
                String[] arr = s.split("-");
                if (!arr[0].equals("card")) {
                    map.put(
                            Integer.parseInt(arr[0].trim()),
                            Integer.parseInt(arr[1].trim())
                    );
                } else {
                    discountService.create(Integer.parseInt(arr[1].trim()), user);
                }
            });
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException exception) {
            log.error("You entered not right data. Please look at the example");
        }
        return map;
    }


}
