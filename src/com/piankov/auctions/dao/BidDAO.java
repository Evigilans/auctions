package com.piankov.auctions.dao;

import com.piankov.auctions.entity.Bid;
import com.piankov.auctions.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BidDAO extends AbstractDAO<Bid> {
    public BidDAO() {
        this.connection = ConnectionPool.getInstance().takeConnection();
    }

    @Override
    public List<Bid> findAll() throws SQLException {
        return null;
    }

    @Override
    public Bid findById(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean delete(Bid entity) {
        return false;
    }

    @Override
    public long create(Bid bid) throws SQLException {
        String query = "INSERT INTO BID (CLIENT_ID, AUCTION_ID, VALUE) VALUES (?, ?, ?)";
        PreparedStatement statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, String.valueOf(bid.getClientId()));
        statement.setString(2, String.valueOf(bid.getAuctionId()));
        statement.setString(3, String.valueOf(bid.getValue()));

        System.out.println(statement.toString());

        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getLong(1);
    }

    @Override
    public Bid update(Bid entity) {
        return null;
    }


    public Bid findMaxBidByAuctionId(String auctionId) throws SQLException {
        String query = "SELECT ID, CLIENT_ID, AUCTION_ID, MAX(VALUE) FROM BID WHERE AUCTION_ID = ? GROUP BY AUCTION_ID";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1, auctionId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            Bid bid = new Bid();
            bid.setId(rs.getInt("ID"));
            bid.setAuctionId(Long.parseLong(auctionId));
            bid.setClientId(rs.getInt("CLIENT_ID"));
            bid.setValue(rs.getInt("MAX(VALUE)"));
            return bid;
        }
        return null;
    }
}
