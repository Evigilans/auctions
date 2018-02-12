package com.piankov.auctions.entity;

public enum AuctionState {
    ON_VERIFICATION(1),
    IN_PROGRESS(2),
    SUCCESSFUL(3),
    UNSUCCESSFUL(4),
    WITHDRAW_FROM_SALES(5);

    private final int value;

    AuctionState(int value) {
        this.value = value;
    }

    public static AuctionState getStateFromValue(int value) {
        return AuctionState.values()[value - 1];
    }

    public int getValue() {
        return value;
    }
}