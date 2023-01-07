package com.clevertec.dao.db;

import com.clevertec.entity.Client;
import com.clevertec.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDao {
    private static final UserDao INSTANCE = new UserDao();

    public static UserDao getInstance() {
        return INSTANCE;
    }


    public List<User> findAll() {



        return List.of(
                new Client(),
                new Client(),
                new Client()
                );

    }
}
