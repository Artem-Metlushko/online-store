package com.clevertec.entity.util;

public enum Role {
    CLIENT(1),
    SELLER(2),
    ADMIN(3);

    public final int role;

    public int getRole() {
        return role;
    }

    Role(int role) {

        this.role = role;
    }

}
