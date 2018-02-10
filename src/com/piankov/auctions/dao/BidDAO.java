package com.piankov.auctions.dao;

import com.piankov.auctions.creator.BidCreator;
import com.piankov.auctions.entity.Bid;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BidDAO extends AbstractDAO<Bid> {
    private static final String FIND_ALL_BIDS = "SELECT * FROM BID";
    private static final String FIND_BID_BY_ID = "SELECT * FROM BID WHERE ID = ?";
    private static final String DELETE_BID_BY_ID = "DELETE FROM BID WHERE ID = ?";
    private static final String UPDATE_BID = "UPDATE BID SET CLIENT_ID = ?, AUCTION_ID = ?, VALUE = ? WHERE ID = ?";
    private static final String CREATE_BID = "INSERT INTO BID (CLIENT_ID, AUCTION_ID, VALUE) VALUES (?, ?, ?)";
    private static final String FIND_MAXIMAL_BID_FOR_AUCTION = "SELECT ID, CLIENT_ID, AUCTION_ID, MAX(VALUE) AS VALUE FROM BID WHERE AUCTION_ID = ? GROUP BY AUCTION_ID";
    private static final String HAS_AUCTION_ANY_BID = "SELECT * FROM BID WHERE AUCTION_ID = ?";

    public BidDAO() {
        super();
    }

    @Override
    public List<Bid> findAll() throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(FIND_ALL_BIDS);

        ResultSet rs = statement.executeQuery();

        BidCreator bidCreator = new BidCreator();
        return bidCreator.buildListFromResultSet(rs);
    }

    @Override
    public Bid findById(String bidId) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(FIND_BID_BY_ID);

        statement.setString(1, bidId);
        ResultSet rs = statement.executeQuery();

        BidCreator bidCreator = new BidCreator();
        return bidCreator.buildEntityFromResultSet(rs);
    }

    //TODO: CHECK THIS METHOD
    @Override
    public boolean delete(String bidId) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(DELETE_BID_BY_ID, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, bidId);

        return statement.execute();
    }

    @Override
    public boolean delete(Bid bid) throws SQLException {
        return delete(String.valueOf(bid.getId()));
    }

    @Override
    public long create(Bid bid) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(CREATE_BID, Statement.RETURN_GENERATED_KEYS);

        statement.setLong(1, bid.getClientId());
        System.out.println("000");
        statement.setLong(2, bid.getAuctionId());
        System.out.println("000");
        statement.setLong(3, bid.getValue());
        System.out.println("000");
        statement.executeUpdate();

        System.out.println("000");
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        System.out.println("000");
        return resultSet.getLong(1);
    }

    @Override
    public Bid update(Bid bid) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(UPDATE_BID, Statement.RETURN_GENERATED_KEYS);

        statement.setLong(1, bid.getClientId());
        statement.setLong(2, bid.getAuctionId());
        statement.setLong(3, bid.getValue());
        statement.setLong(4, bid.getId());
        statement.executeUpdate();

        String bidId = String.valueOf(bid.getId());
        return findById(bidId);
    }

    public Bid findMaxBidByAuctionId(String auctionId) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(FIND_MAXIMAL_BID_FOR_AUCTION);

        statement.setString(1, auctionId);
        ResultSet rs = statement.executeQuery();

        BidCreator bidCreator = new BidCreator();
        return bidCreator.buildEntityFromResultSet(rs);
    }

    public boolean hasAnyBid(long auctionId) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(HAS_AUCTION_ANY_BID);

        statement.setLong(1, auctionId);
        ResultSet rs = statement.executeQuery();

        return rs.next();
    }
}