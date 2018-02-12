package com.piankov.auctions.entity;

import java.sql.Blob;

public class User extends Entity {
    private String login;
    private String passwordHash;
    private String name;
    private String email;
    private int balance;
    private UserCategory category;
    private Blob avatar;

    public User() {}

    public User(String login, String passwordHash, String name, String email, int balance, UserCategory category, Blob avatar) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.category = category;
        this.avatar = avatar;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Blob getAvatar() {
        return avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (balance != user.balance) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (passwordHash != null ? !passwordHash.equals(user.passwordHash) : user.passwordHash != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (category != user.category) return false;
        return avatar != null ? avatar.equals(user.avatar) : user.avatar == null;
    }

    public boolean isClient() {
        return this.category == UserCategory.CLIENT;
    }

    public boolean isAdmin() {
        return this.category == UserCategory.ADMIN;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + balance;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", category=" + category +
                ", avatar=" + avatar +
                ", id=" + id +
                "} " + super.toString();
    }
}
