package com.piankov.auctions.entity;

import java.util.Objects;

public class Bid extends Entity implements Comparable<Bid> {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bid)) return false;
        Bid bid = (Bid) o;
        return clientId == bid.clientId &&
                auctionId == bid.auctionId &&
                value == bid.value;
    }

    @Override
    public int hashCode() {

        return Objects.hash(clientId, auctionId, value);
    }

    @Override
    public String toString() {
        return "Bid{" +
                "clientId=" + clientId +
                ", auctionId=" + auctionId +
                ", value=" + value +
                ", id=" + id +
                "} " + super.toString();
    }
}
