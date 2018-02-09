package com.piankov.auctions.entity;

public enum UserCategory {
    CLIENT(0), ADMIN(1);

    UserCategory(int value) {
    }

    public static UserCategory getCategoryFromValue(int value) {
        return UserCategory.values()[value];
    }
}
