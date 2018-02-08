package com.piankov.auctions.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Payment extends Entity {
    private long fromClient;
    private long toClient;
    private int amount;
    private LocalDateTime date;

    public Payment() {}

    public Payment(long id, long fromClient, long toClient, int amount, LocalDateTime date) {
        this.id = id;
        this.fromClient = fromClient;
        this.toClient = toClient;
        this.amount = amount;
        this.date = date;
    }

    public long getFromClient() {
        return fromClient;
    }

    public void setFromClient(long fromClient) {
        this.fromClient = fromClient;
    }

    public long getToClient() {
        return toClient;
    }

    public void setToClient(long toClient) {
        this.toClient = toClient;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return fromClient == payment.fromClient &&
                toClient == payment.toClient &&
                amount == payment.amount &&
                Objects.equals(date, payment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromClient, toClient, amount, date);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "fromClient=" + fromClient +
                ", toClient=" + toClient +
                ", amount=" + amount +
                ", date=" + date +
                ", id=" + id +
                "} " + super.toString();
    }
}
