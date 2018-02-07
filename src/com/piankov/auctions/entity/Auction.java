package com.piankov.auctions.entity;

import java.time.LocalDateTime;

public class Auction extends Entity {
    private long id;
    private Lot lot;
    private long stateId;
    private long typeId;
    private Bid currentMaximalBid;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Auction() {
    }

    public Auction(long id, Lot lot, long stateId, long typeId, Bid currentMaximalBid, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.lot = lot;
        this.stateId = stateId;
        this.typeId = typeId;
        this.currentMaximalBid = currentMaximalBid;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public Bid getCurrentMaximalBid() {
        return currentMaximalBid;
    }

    public void setCurrentMaximalBid(Bid currentMaximalBid) {
        this.currentMaximalBid = currentMaximalBid;
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
}
