package com.clevertec.entity;

import com.clevertec.entity.util.Role;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
public class Client extends User implements Serializable {
    private Role role = Role.CLIENT;


    public static void main(String[] args) {
        System.out.println(new User().getRole());
        System.out.println(new Client().getRole());

        Client client = new Client();
//        System.out.println(client.getRole().getRole());
    }
}
