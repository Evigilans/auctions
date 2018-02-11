package com.piankov.auctions.entity;

import java.util.Objects;

public class User extends Entity {
    private String login;
    private char[] passwordHash;
    private String name;
    private String email;
    private int balance;
    private UserCategory category;

    public User(String login, char[] passwordHash, String name, String email, int balance, UserCategory category) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.category = category;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public char[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(char[] passwordHash) {
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

    public boolean isClient() {
        return category == UserCategory.CLIENT;
    }

    public boolean isAdvanced() {
        return category == UserCategory.ADVANCED;
    }

    public boolean isAdmin() {
        return category == UserCategory.ADMIN;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return balance == user.balance &&
                Objects.equals(login, user.login) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                category == user.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, name, email, balance, category);
    }

    @Override
    public String toString() {
        String sb = "User{" + "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", category=" + category +
                ", id=" + id +
                '}';
        return sb;
    }
}
