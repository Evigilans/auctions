package com.piankov.auctions.entity;

public enum UserCategory {
    CLIENT(0), ADVANCED(1), ADMIN(2);

    public final int value;

    UserCategory(int value) {
        this.value = value;
    }

    public static UserCategory getCategoryFromValue(int value) {
        return UserCategory.values()[value];
    }
}
