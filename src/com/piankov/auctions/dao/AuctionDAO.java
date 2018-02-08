package com.piankov.auctions.dao;

import com.piankov.auctions.creator.AuctionCreator;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuctionDAO extends AbstractDAO<Auction> {
    private static final String FIND_ALL_AUCTIONS = "SELECT * FROM AUCTION";
    private static final String FIND_AUCTION_BY_ID = "SELECT * FROM AUCTION WHERE ID = ?";
    private static final String DELETE_AUCTION_BY_ID = "DELETE FROM AUCTION WHERE ID = ?";
    //TODO: DATES
    private static final String UPDATE_AUCTION = "UPDATE AUCTION SET LOT_ID = ?, AUCTION_STATE_ID = ?, AUCTION_TYPE_ID = ?  WHERE ID = ?";
    private static final String CREATE_AUCTION = "INSERT INTO AUCTION () VALUES (?, ?, ?)";
    private static final String FIND_AUCTIONS_BY_STATE = "SELECT * FROM AUCTION WHERE AUCTION_STATE_ID = ?";
    private static final String FIND_OUTDATED_AUCTIONS = "SELECT ID FROM AUCTION WHERE (AUCTION_STATE_ID = 2 and END_DATE < NOW())";
    private static final String END_OUTDATED_AUCTIONS = "UPDATE AUCTION SET AUCTION_STATE_ID = ? WHERE ID = ?";

    public AuctionDAO() {
        this.connection = ConnectionPool.getInstance().takeConnection();
    }

    @Override
    public List<Auction> findAll() throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(FIND_ALL_AUCTIONS);
        ResultSet rs = statement.executeQuery();
        AuctionCreator auctionCreator = new AuctionCreator();
        return auctionCreator.buildListFromResultSet(rs);
    }

    @Override
    public Auction findById(String id) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(FIND_AUCTION_BY_ID);
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();
        AuctionCreator auctionCreator = new AuctionCreator();
        return auctionCreator.buildEntityFromResultSet(rs);
    }

    @Override
    public boolean delete(String auctionId) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(DELETE_AUCTION_BY_ID, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, auctionId);
        return statement.execute();
    }

    @Override
    public boolean delete(Auction auction) throws SQLException {
        return delete(String.valueOf(auction.getId()));
    }

    @Override
    public long create(Auction auction) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(CREATE_AUCTION, Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, auction.getLot().getId());
        statement.setLong(2, auction.getState().ordinal());
        statement.setLong(3, auction.getType().ordinal());
        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getLong(1);
    }

    @Override
    public Auction update(Auction auction) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(UPDATE_AUCTION, Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, auction.getLot().getId());
        statement.setLong(2, auction.getState().ordinal());
        statement.setLong(3, auction.getType().ordinal());
        statement.setLong(4, auction.getId());
        statement.executeUpdate();

        String bidId = String.valueOf(auction.getId());
        return findById(bidId);
    }

    public List<Auction> findAuctionsByState(int stateId) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(FIND_AUCTIONS_BY_STATE);
        statement.setInt(1, stateId);
        ResultSet rs = statement.executeQuery();
        AuctionCreator auctionCreator = new AuctionCreator();
        return auctionCreator.buildListFromResultSet(rs);
    }

    public void endOutdatedAuctions() throws SQLException {
        List<String> outdatedAuctions = findOutdatedAuctions();
        BidDAO bidDAO = new BidDAO();

        for (String auctionId : outdatedAuctions) {
            PreparedStatement statement = this.connection.prepareStatement(END_OUTDATED_AUCTIONS);
            if (bidDAO.hasAnyBid(auctionId)) {
                statement.setInt(1, 3);
            } else {
                statement.setInt(1, 4);
            }
            statement.setString(2, auctionId);
            statement.executeUpdate();
        }
    }

    private List<String> findOutdatedAuctions() throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(FIND_OUTDATED_AUCTIONS);
        ResultSet rs = statement.executeQuery();

        List<String> outdatedAuctions = new ArrayList<>();
        while (rs.next()) {
            outdatedAuctions.add(rs.getString("ID"));
        }
        return outdatedAuctions;
    }
}
