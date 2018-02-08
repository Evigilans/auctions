package com.piankov.auctions.entity;

import java.util.Objects;

public class Lot extends Entity {
    private User owner;
    private int startPrice;
    private String name;
    private String description;

    public Lot() {
    }

    public Lot(long id, User owner, int startPrice, String name, String description) {
        this.id = id;
        this.owner = owner;
        this.startPrice = startPrice;
        this.name = name;
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lot)) return false;
        Lot lot = (Lot) o;
        return startPrice == lot.startPrice &&
                Objects.equals(owner, lot.owner) &&
                Objects.equals(name, lot.name) &&
                Objects.equals(description, lot.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, startPrice, name, description);
    }

    @Override
    public String toString() {
        return "Lot{" +
                "owner=" + owner +
                ", startPrice=" + startPrice +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                "} " + super.toString();
    }
}
