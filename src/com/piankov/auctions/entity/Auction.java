package com.piankov.auctions.entity;

import java.time.LocalDateTime;

public class Auction extends Entity {
    private Lot lot;
    private AuctionState state;
    private AuctionType type;
    private int startPrice;
    private int daysDurations;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Bid currentMaximalBid;

    public Auction() {
    }

    public Auction(Lot lot, AuctionState state, AuctionType type, int startPrice, int daysDurations, LocalDateTime startDate, LocalDateTime endDate, Bid currentMaximalBid) {
        this.lot = lot;
        this.state = state;
        this.type = type;
        this.startPrice = startPrice;
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

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
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
        if (!super.equals(o)) return false;

        Auction auction = (Auction) o;

        if (startPrice != auction.startPrice) return false;
        if (daysDurations != auction.daysDurations) return false;
        if (lot != null ? !lot.equals(auction.lot) : auction.lot != null) return false;
        if (state != auction.state) return false;
        if (type != auction.type) return false;
        if (startDate != null ? !startDate.equals(auction.startDate) : auction.startDate != null) return false;
        if (endDate != null ? !endDate.equals(auction.endDate) : auction.endDate != null) return false;
        return currentMaximalBid != null ? currentMaximalBid.equals(auction.currentMaximalBid) : auction.currentMaximalBid == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (lot != null ? lot.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + startPrice;
        result = 31 * result + daysDurations;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (currentMaximalBid != null ? currentMaximalBid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "lot=" + lot +
                ", state=" + state +
                ", type=" + type +
                ", startPrice=" + startPrice +
                ", daysDurations=" + daysDurations +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", currentMaximalBid=" + currentMaximalBid +
                ", id=" + id +
                "} " + super.toString();
    }
}
