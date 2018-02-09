package com.piankov.auctions.entity;

public enum AuctionType {
    DIRECT(1), REVERSE(2);

    AuctionType(int value) {
    }

    public static AuctionType getTypeFromValue(int value) {
        return AuctionType.values()[value];
    }
}
