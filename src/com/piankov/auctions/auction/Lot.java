package com.piankov.auctions.auction;

public class Lot {
    private long id;
    private long ownerId;
    private int startPrice;
    private String name;
    private String Description;

    public Lot() {
    }

    public Lot(long id, long ownerId, int startPrice, String name, String description) {
        this.id = id;
        this.ownerId = ownerId;
        this.startPrice = startPrice;
        this.name = name;
        Description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
