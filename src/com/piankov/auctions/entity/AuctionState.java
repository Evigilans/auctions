package com.piankov.auctions.entity;

public enum AuctionState {
    //TODO: как получать значения???

    ON_VERIFICATION(1), IN_PROGRESS(2), SUCCESSFUL(3), UNSUCCESSFUL(4), WITHDRAW_FROM_SALES(5);

    public int value;

    AuctionState(int value) {
    }

    public static AuctionState getStateFromValue(int value) {
        return AuctionState.values()[value];
    }
}