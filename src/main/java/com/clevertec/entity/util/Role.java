package com.clevertec.entity.util;

import lombok.Data;


public enum Role {
    CLIENT(3),
    SELLER(2),
    ADMIN(1);

    public final int role;

    public int getRole() {
        return role;
    }
    Role(int role) {

        this.role = role;
    }

}
