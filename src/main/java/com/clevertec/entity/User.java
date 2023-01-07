package com.clevertec.entity;

import com.clevertec.entity.util.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

@Data
public class User implements Serializable {
    private UUID id;
    private String name;
    private String login;
    private String password;
    private String emailAddress;
    private Role role;

}


