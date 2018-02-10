package com.piankov.auctions.dao;

import com.piankov.auctions.creator.AuctionCreator;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.AuctionState;

import java.sql.*;
import java.util.List;

public class AuctionDAO extends AbstractDAO<Auction> {
    private static final String FIND_ALL_AUCTIONS = "SELECT * FROM AUCTION";
    private static final String FIND_AUCTION_BY_ID = "SELECT * FROM AUCTION WHERE ID = ?";
    private static final String DELETE_AUCTION_BY_ID = "DELETE FROM AUCTION WHERE ID = ?";
    private static final String UPDATE_AUCTION = "UPDATE AUCTION SET LOT_ID = ?, AUCTION_STATE_ID = ?, AUCTION_TYPE_ID = ?, DAYS_DURATION = ?, START_DATE = ?, END_DATE = ? WHERE ID = ?";
    private static final String CREATE_AUCTION = "INSERT INTO AUCTION (LOT_ID, AUCTION_STATE_ID, AUCTION_TYPE_ID, DAYS_DURATION, START_DATE, END_DATE) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_AUCTIONS_BY_STATE = "SELECT * FROM AUCTION WHERE AUCTION_STATE_ID = ?";
    private static final String FIND_OUTDATED_AUCTIONS = "SELECT * FROM AUCTION WHERE (AUCTION_STATE_ID = 2 and END_DATE < NOW())";
    private static final String END_OUTDATED_AUCTIONS = "UPDATE AUCTION SET AUCTION_STATE_ID = ? WHERE ID = ?";

    public AuctionDAO() {
        super();
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
        statement.setLong(4, auction.getDaysDurations());
        statement.setTimestamp(5, Timestamp.valueOf(auction.getStartDate()));
        statement.setTimestamp(6, Timestamp.valueOf(auction.getEndDate()));
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
        try (BidDAO bidDAO = new BidDAO()) {
            List<Auction> outdatedAuctions = findOutdatedAuctions();
            for (Auction auction : outdatedAuctions) {
                //TODO: Куда вынести statement???
                PreparedStatement statement = this.connection.prepareStatement(END_OUTDATED_AUCTIONS);

                if (bidDAO.hasAnyBid(auction.getId())) {
                    statement.setInt(1, AuctionState.SUCCESSFUL.ordinal());
                } else {
                    statement.setInt(1, AuctionState.UNSUCCESSFUL.ordinal());
                }
                statement.setLong(2, auction.getId());
                statement.executeUpdate();
            }
        }
    }

    private List<Auction> findOutdatedAuctions() throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(FIND_OUTDATED_AUCTIONS);

        ResultSet rs = statement.executeQuery();

        AuctionCreator auctionCreator = new AuctionCreator();
        return auctionCreator.buildListFromResultSet(rs);
    }
}
