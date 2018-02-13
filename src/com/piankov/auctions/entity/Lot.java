package com.piankov.auctions.entity;

public class Lot extends Entity {
    private User owner;
    private String name;
    private String description;

    public Lot() {
    }

    public Lot(long id, User owner, String name, String description) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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
        if (!super.equals(o)) return false;

        Lot lot = (Lot) o;

        if (owner != null ? !owner.equals(lot.owner) : lot.owner != null) return false;
        if (name != null ? !name.equals(lot.name) : lot.name != null) return false;
        return description != null ? description.equals(lot.description) : lot.description == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "owner=" + owner +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                "} " + super.toString();
    }
}
