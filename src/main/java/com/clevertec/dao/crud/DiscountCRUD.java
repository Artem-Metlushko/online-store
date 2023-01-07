package com.clevertec.dao.crud;

import com.clevertec.entity.Discount;
import com.clevertec.entity.Product;
import com.clevertec.entity.User;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.io.*;
import java.nio.file.Path;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Log4j
@NoArgsConstructor(access = PRIVATE)
public class DiscountCRUD {
    private static final DiscountCRUD discountCRUD = new DiscountCRUD();
    public static DiscountCRUD getInstance() {
        return discountCRUD;
    }
    public void create(Discount discount, User user) {
        File file = Path.of("save", "discounts", user.getName() + "_" + user.getLogin() + ".txt").toFile();
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file))) {
            stream.writeObject(discount);
        } catch (FileNotFoundException e) {
            log.error("Discount not create" + e.getMessage());
        } catch (IOException e) {
            log.error("Error IOException in Class ");
        }
    }


    public Discount read(File file) {
        Discount discount = new Discount();
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));) {
            discount = (Discount) stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.info("Discount not exist");
        }
        return discount;
    }



    public Optional<File> findByNumber( User user) {
        File file = Path.of("save", "discounts", user.getName() + "_" + user.getLogin() + ".txt").toFile();
        Discount discount = read(file);
        if (file.getName().equals(user.getName() + "_" + user.getLogin() + ".txt")) {
            return Optional.of(file);
        } else log.info("Discount card not found ");
        return Optional.empty();

    }

    public boolean delete(User user) {
        return findByNumber(user).isPresent() &&
                findByNumber(user).get().delete();
    }
}
