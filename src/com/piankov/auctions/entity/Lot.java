package com.piankov.auctions.entity;

public class Lot extends Entity {
    private long id;
    private long ownerId;
    private int startPrice;
    private String name;
    private String description;

    public Lot() {
    }

    public Lot(long id, long ownerId, int startPrice, String name, String description) {
        this.id = id;
        this.ownerId = ownerId;
        this.startPrice = startPrice;
        this.name = name;
        this.description = description;
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
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
