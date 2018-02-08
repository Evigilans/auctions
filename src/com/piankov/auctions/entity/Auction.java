package com.piankov.auctions.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Auction extends Entity {
    private Lot lot;
    private AuctionState state;
    private AuctionType type;
    private int daysDurations;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Bid currentMaximalBid;

    public Auction() {
    }

    public Auction(long id, Lot lot, AuctionState state, AuctionType type, int daysDurations, LocalDateTime startDate, LocalDateTime endDate, Bid currentMaximalBid) {
        this.id = id;
        this.lot = lot;
        this.state = state;
        this.type = type;
        this.daysDurations = daysDurations;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentMaximalBid = currentMaximalBid;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public AuctionState getState() {
        return state;
    }

    public void setState(AuctionState state) {
        this.state = state;
    }

    public AuctionType getType() {
        return type;
    }

    public void setType(AuctionType type) {
        this.type = type;
    }

    public int getDaysDurations() {
        return daysDurations;
    }

    public void setDaysDurations(int daysDurations) {
        this.daysDurations = daysDurations;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Bid getCurrentMaximalBid() {
        return currentMaximalBid;
    }

    public void setCurrentMaximalBid(Bid currentMaximalBid) {
        this.currentMaximalBid = currentMaximalBid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auction)) return false;
        Auction auction = (Auction) o;
        return daysDurations == auction.daysDurations &&
                Objects.equals(lot, auction.lot) &&
                state == auction.state &&
                type == auction.type &&
                Objects.equals(startDate, auction.startDate) &&
                Objects.equals(endDate, auction.endDate) &&
                Objects.equals(currentMaximalBid, auction.currentMaximalBid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(lot, state, type, daysDurations, startDate, endDate, currentMaximalBid);
    }

    @Override
    public String toString() {
        return "Auction{" +
                "lot=" + lot +
                ", state=" + state +
                ", type=" + type +
                ", daysDurations=" + daysDurations +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", currentMaximalBid=" + currentMaximalBid +
                ", id=" + id +
                "} " + super.toString();
    }
}
