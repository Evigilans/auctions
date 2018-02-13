package com.piankov.auctions.dao;

import com.piankov.auctions.creator.BidCreator;
import com.piankov.auctions.entity.Bid;
import com.piankov.auctions.exception.DAOException;
import com.piankov.auctions.exception.EntityCreationException;

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
    public List<Bid> findAll() throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(FIND_ALL_BIDS);

            ResultSet rs = statement.executeQuery();

            BidCreator bidCreator = new BidCreator();
            return bidCreator.createListFromResultSet(rs);
        } catch (SQLException | EntityCreationException e) {
            throw new DAOException("And exception occurred during finding all bids.", e);
        }
    }

    @Override
    public Bid findById(String bidId) throws DAOException {
        try {
            BidCreator bidCreator = new BidCreator();
            PreparedStatement statement = this.connection.prepareStatement(FIND_BID_BY_ID);

            statement.setString(1, bidId);

            ResultSet rs = statement.executeQuery();
            return bidCreator.createEntityFromResultSet(rs);
        } catch (SQLException | EntityCreationException e) {
            throw new DAOException("And exception occurred during finding bid by ID.", e);
        }
    }

    @Override
    public boolean delete(String bidId) throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(DELETE_BID_BY_ID, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, bidId);

            return statement.execute();
        } catch (SQLException e) {
            throw new DAOException("And exception occurred during deleting bid.", e);
        }
    }

    @Override
    public boolean delete(Bid bid) throws DAOException {
        return delete(String.valueOf(bid.getId()));
    }

    @Override
    public long create(Bid bid) throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(CREATE_BID, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, bid.getClientId());
            statement.setLong(2, bid.getAuctionId());
            statement.setLong(3, bid.getValue());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DAOException("And exception occurred during creating bid.", e);
        }
    }

    @Override
    public Bid update(Bid bid) throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(UPDATE_BID, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, bid.getClientId());
            statement.setLong(2, bid.getAuctionId());
            statement.setLong(3, bid.getValue());
            statement.setLong(4, bid.getId());
            statement.executeUpdate();

            String bidId = String.valueOf(bid.getId());
            return findById(bidId);
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }

    public Bid findMaxBidByAuctionId(String auctionId) throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(FIND_MAXIMAL_BID_FOR_AUCTION);

            statement.setString(1, auctionId);
            ResultSet rs = statement.executeQuery();

            BidCreator bidCreator = new BidCreator();
            return bidCreator.createEntityFromResultSet(rs);
        } catch (SQLException | EntityCreationException e) {
            throw new DAOException("And exception occurred during finding maximal bid by auction ID.", e);
        }
    }

    public boolean hasAnyBid(long auctionId) throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(HAS_AUCTION_ANY_BID);

            statement.setLong(1, auctionId);
            ResultSet rs = statement.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            throw new DAOException("And exception occurred during checking if auction has any bid.", e);
        }
    }
}
