package com.piankov.auctions.entity;

public class Client extends Entity {
    private long id;
    private String login;
    private String passwordHash;
    private String name;
    private String email;
    private int balance;
    private int category;

    public Client() {
    }

    public Client(long id, String login, String passwordHash, String name, String email, int balance, int category) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isAdmin() {
        return category == 1;
    }
}
