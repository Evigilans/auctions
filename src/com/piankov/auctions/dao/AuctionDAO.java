package com.piankov.auctions.dao;

import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuctionDAO extends AbstractDAO<Auction> {
    public AuctionDAO() {
        this.connection = ConnectionPool.getInstance().takeConnection();
    }

    @Override
    public List<Auction> findAll() {
        List<Auction> auctions = new ArrayList<>();
        return auctions;
    }

    @Override
    public Auction findById(String id) throws SQLException {
        LotDAO lotDAO = new LotDAO();
        BidDAO bidDAO = new BidDAO();
        String query = "SELECT * FROM AUCTION WHERE ID = ? AND AUCTION_STATE_ID = 2";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            Auction auction = new Auction();
            auction.setId(Long.parseLong(id));
            auction.setLot(lotDAO.findByAuctionId(id));
            auction.setStateId(rs.getInt("AUCTION_STATE_ID"));
            auction.setTypeId(rs.getInt("AUCTION_TYPE_ID"));
            auction.setCurrentMaximalBid(bidDAO.findMaxBidByAuctionId(id));
            return auction;
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean delete(Auction entity) {
        return false;
    }

    @Override
    public long create(Auction auction) throws SQLException {
        String query = "INSERT INTO AUCTION (LOT_ID, AUCTION_STATE_ID, AUCTION_TYPE_ID, START_DATE) VALUES (?, ?, ?, '2017-10-12 17:00:00')";
        PreparedStatement statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, String.valueOf(auction.getLot().getId()));
        statement.setString(2, String.valueOf(auction.getStateId()));
        statement.setString(3, String.valueOf(auction.getTypeId()));
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getLong(1);
    }

    @Override
    public Auction update(Auction entity) {
        return null;
    }

    public List<Auction> findActiveAuctions() throws SQLException {
        LotDAO lotDAO = new LotDAO();
        BidDAO bidDAO = new BidDAO();
        List<Auction> auctions = new ArrayList<>();
        String query = "SELECT * FROM AUCTION WHERE AUCTION_STATE_ID = 2";
        PreparedStatement statement = this.connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Auction auction = new Auction();
            int auctionId = rs.getInt("ID");
            auction.setId(auctionId);
            auction.setLot(lotDAO.findByAuctionId(String.valueOf(auctionId)));
            auction.setStateId(rs.getInt("AUCTION_STATE_ID"));
            auction.setTypeId(rs.getInt("AUCTION_TYPE_ID"));
            auction.setCurrentMaximalBid(bidDAO.findMaxBidByAuctionId(String.valueOf(auctionId)));
            auctions.add(auction);
        }
        return auctions;
    }
}
