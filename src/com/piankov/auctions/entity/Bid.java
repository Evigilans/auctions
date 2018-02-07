package com.piankov.auctions.entity;

public class Bid extends Entity implements Comparable<Bid> {
    private long id;
    private long clientId;
    private long auctionId;
    private int value;

    public Bid() {
    }

    public Bid(long id, long clientId, long auctionId, int value) {
        this.id = id;
        this.clientId = clientId;
        this.auctionId = auctionId;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(long auctionId) {
        this.auctionId = auctionId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Bid bid) {
        return Integer.compare(this.value, bid.value);
    }
}
