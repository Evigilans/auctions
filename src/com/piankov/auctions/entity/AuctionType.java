package com.piankov.auctions.entity;

public enum AuctionType {
    DIRECT(1),
    REVERSE(2);

    private final int value;

    AuctionType(int value) {
        this.value = value;
    }

    public static AuctionType getTypeFromValue(int value) {
        return AuctionType.values()[value - 1];
    }

    public int getValue() {
        return value;
    }
}
