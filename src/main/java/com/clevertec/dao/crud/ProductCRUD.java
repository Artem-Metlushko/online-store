package com.clevertec.dao.crud;

import com.clevertec.entity.Product;
import com.clevertec.entity.User;
import com.clevertec.exception.CrudException;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


import java.io.*;
import java.nio.file.Path;
import java.util.*;

import static lombok.AccessLevel.PRIVATE;

@Log4j
@NoArgsConstructor(access = PRIVATE)
public class ProductCRUD implements CRUD<Product> {
    private static final ProductCRUD productCRUD = new ProductCRUD();

    public static ProductCRUD getInstance() {
        return productCRUD;
    }

    private final File folder = Path.of("save", "products").toFile();

    @Override
    public void create(Product product, User user) {
        File file = Path.of("save", "products", product.getId() + ".txt").toFile();
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file))) {
            stream.writeObject(product);
        } catch (FileNotFoundException e) {
            log.error("File not exist" + e.getMessage());
        } catch (IOException e) {
            throw new CrudException(e);
        }
    }

    @Override
    public Product read(File file) {
        Product product = new Product();
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
            product = (Product) stream.readObject();
        } catch (FileNotFoundException e) {
            log.error("File not exist", e);
        } catch (IOException | ClassNotFoundException e) {
            log.error(e.getMessage());
        }
        return product;
    }

    @Override
    public boolean update(int id) {
        return delete(id);
    }

    @Override
    public boolean delete(int id) {
        return findById(id).isPresent()
                && findById(id).get().delete();
    }

    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        for (File i : Objects.requireNonNull(folder.listFiles())) {
            Product product = read(i);
            list.add(product);
        }
        return list;
    }

    public Optional<File> findById(int id) {
        File[] files = Objects.requireNonNull(folder.listFiles());
        try {
            for (File file :files ) {
                if ((id + ".txt").equals(file.getName())) {
                    return Optional.of(file);
                }
            }
        } catch (NullPointerException e) {
            log.info("You entered id isn't right");
        }
        return Optional.empty();

    }


}
