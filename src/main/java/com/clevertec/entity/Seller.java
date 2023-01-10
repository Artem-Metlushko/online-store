package com.clevertec.entity;

import com.clevertec.entity.util.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class Seller extends User implements Serializable {
    private Role role = Role.SELLER;
}
