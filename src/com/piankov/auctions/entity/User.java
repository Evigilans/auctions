package com.piankov.auctions.entity;

public class User extends Entity {
    private String email;
    private String passwordHash;
    private String name;
    private int balance;
    private UserCategory category;

    public User() {
    }

    public User(String email, String passwordHash, String name, int balance, UserCategory category) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
        this.balance = balance;
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public UserCategory getCategory() {
        return category;
    }

    public void setCategory(UserCategory category) {
        this.category = category;
    }

    public boolean isClient() {
        return this.category == UserCategory.CLIENT;
    }

    public boolean isAdmin() {
        return this.category == UserCategory.ADMIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (balance != user.balance) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (passwordHash != null ? !passwordHash.equals(user.passwordHash) : user.passwordHash != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return category == user.category;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + balance;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", category=" + category +
                ", id=" + id +
                "} " + super.toString();
    }
}
