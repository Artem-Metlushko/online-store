package com.clevertec.dao.crud;

import com.clevertec.entity.Product;
import com.clevertec.entity.ProductInBasket;
import com.clevertec.entity.User;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

import static lombok.AccessLevel.PRIVATE;

@Log4j
@NoArgsConstructor(access = PRIVATE)
public class ProductInBasketCRUD {
    private static final ProductInBasketCRUD productInBasketCRUD = new ProductInBasketCRUD();

    public static ProductInBasketCRUD getInstance() {
        return productInBasketCRUD;
    }

    public void create(ProductInBasket productInBasket, User user) {
        File file = Path.of(getFolderUser(user).getAbsolutePath(), productInBasket.getId() + ".txt").toFile();
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file))) {
            stream.writeObject(productInBasket);
        } catch (FileNotFoundException e) {
            log.error("File not exist" + e.getMessage());
        } catch (IOException e) {
            log.error("Error IOException in Class{} ", e);
        }
    }

    public ProductInBasket read(File file) {
        ProductInBasket productInBasket = new ProductInBasket(new Product());
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));) {
            productInBasket = (ProductInBasket) stream.readObject();
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            log.error("File not exist");
        }
        return productInBasket;
    }

    public boolean delete(int id, User user) {
        return findById(id, user).isPresent() && findById(id, user).get().delete();

    }

    public List<ProductInBasket> findAll(User user) {
        List<ProductInBasket> list = new ArrayList<>();
        File[] files = Objects.requireNonNull(getFolderUser(user).listFiles());
        if (files.length != 0) {
            Arrays.stream(files)
                    .forEach(file -> list.add(productInBasketCRUD.read(file)));
        }
        return list;
    }

    public Optional<File> findById(int id, User user) {
        File[] files = Objects.requireNonNull(getFolderUser(user).listFiles());
        try {
            for (File file : files) {
                if ((id + ".txt").equals(file.getName())) {
                    return Optional.of(file);
                }
            }
        }catch (NullPointerException e) {
            log.info("You entered id isn't right");
        }
        return Optional.empty();
    }


    private File getFolderUser(User user) {
        File folder = Path.of("save", "productsInBasket",
                user.getName() + "_" + user.getLogin()).toFile();
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder;
    }


}
