package com.clevertec.dao.crud;

import com.clevertec.entity.User;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.io.*;
import java.nio.file.Path;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Log4j
@NoArgsConstructor(access = PRIVATE)
public class UserCRUD {
    private static final UserCRUD clientCRUD = new UserCRUD();

    public static UserCRUD getInstance() {
        return clientCRUD;
    }

    public void create(User user) {
        File file = Path.of("save", "user", user.getLogin() + ".txt").toFile();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))
        ) {
            objectOutputStream.writeObject(user);
        } catch (FileNotFoundException e) {
            log.error(Class.class.getName() + "not create");
        } catch (IOException e) {
            log.error("Error IOException in Class " + Class.class.getName());
        }
    }

    public User read(File file) {
        User user = new User();
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
            user = (User) stream.readObject();
        } catch (FileNotFoundException e) {
            log.error(Class.class.getName() + " not read");
        } catch (IOException e) {
            log.error("Error IOException in Class " + Class.class.getName());
        } catch (ClassNotFoundException e) {
            log.error(Class.class.getName() + " not found");
        } catch (ClassCastException e) {
            log.error(Class.class.getName() + " ClassCastException");
        }
        return user;
    }

    public Optional<User> findByID(User user) {
        File file = Path.of("save", "user", user.getLogin() + ".txt").toFile();
        return Optional.ofNullable(read(file));
    }


}
