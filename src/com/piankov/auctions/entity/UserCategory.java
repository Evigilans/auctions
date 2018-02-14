package com.piankov.auctions.entity;

public enum UserCategory {
    CLIENT(1),
    ADVANCED(2),
    ADMIN(3);

    private final int value;

    UserCategory(int value) {
        this.value = value;
    }

    public static UserCategory getCategoryFromValue(int value) {
        return UserCategory.values()[value];
    }

    public int getValue() {
        return value;
    }
}
