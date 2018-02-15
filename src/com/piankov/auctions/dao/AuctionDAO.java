package com.piankov.auctions.dao;

import com.piankov.auctions.creator.AuctionCreator;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.AuctionState;
import com.piankov.auctions.exception.DAOException;
import com.piankov.auctions.exception.EntityCreationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class AuctionDAO extends AbstractDAO<Auction> {
    private static final Logger LOGGER = LogManager.getLogger(AuctionDAO.class);

    private static final String FIND_ALL_AUCTIONS = "SELECT * FROM AUCTION";
    private static final String FIND_AUCTION_BY_ID = "SELECT * FROM AUCTION WHERE ID = ?";
    private static final String DELETE_AUCTION_BY_ID = "DELETE FROM AUCTION WHERE ID = ?";
    private static final String UPDATE_AUCTION = "UPDATE AUCTION SET LOT_ID = ?, AUCTION_STATE_ID = ?, AUCTION_TYPE_ID = ?, START_PRICE = ?, DAYS_DURATION = ?, START_DATE = ?, END_DATE = ? WHERE ID = ?";
    private static final String CREATE_AUCTION = "INSERT INTO AUCTION (LOT_ID, AUCTION_STATE_ID, AUCTION_TYPE_ID, START_PRICE, DAYS_DURATION, START_DATE, END_DATE) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String CREATE_VERIFYING_AUCTION = "INSERT INTO AUCTION (LOT_ID, AUCTION_STATE_ID, AUCTION_TYPE_ID, START_PRICE, DAYS_DURATION) VALUES (?, 1, ?, ?, ?)";
    private static final String FIND_AUCTIONS_BY_STATE = "SELECT * FROM AUCTION WHERE AUCTION_STATE_ID = ?";
    private static final String FIND_AUCTIONS_BY_USER = "SELECT * FROM AUCTION WHERE LOT_ID IN (SELECT ID FROM LOT WHERE OWNER_ID = ?)";
    private static final String FIND_OUTDATED_AUCTIONS = "SELECT * FROM AUCTION WHERE (AUCTION_STATE_ID = 2 and END_DATE < NOW())";
    private static final String END_OUTDATED_AUCTIONS = "UPDATE AUCTION SET AUCTION_STATE_ID = ? WHERE ID = ?";

    public AuctionDAO() {
        super();
    }

    @Override
    public List<Auction> findAll() throws DAOException {
        LOGGER.info("Searching all auctions in database.");

        try {
            PreparedStatement statement = this.connection.prepareStatement(FIND_ALL_AUCTIONS);

            ResultSet rs = statement.executeQuery();

            AuctionCreator auctionCreator = new AuctionCreator();
            return auctionCreator.createListFromResultSet(rs);
        } catch (SQLException | EntityCreationException e) {
            throw new DAOException("An exception occurred during finding all auction.", e);
        }
    }

    @Override
    public Auction findById(String id) throws DAOException {
        LOGGER.info("Searching auction in database by ID.");

        try {
            PreparedStatement statement = this.connection.prepareStatement(FIND_AUCTION_BY_ID);
            statement.setString(1, id);

            ResultSet rs = statement.executeQuery();

            AuctionCreator auctionCreator = new AuctionCreator();
            return auctionCreator.createEntityFromResultSet(rs);
        } catch (SQLException | EntityCreationException e) {
            throw new DAOException("An exception occurred during finding auction by ID.", e);
        }
    }

    @Override
    public boolean delete(String auctionId) throws DAOException {
        LOGGER.info("Deleting auction from database.");

        try {
            PreparedStatement statement = this.connection.prepareStatement(DELETE_AUCTION_BY_ID, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, auctionId);

            return statement.execute();
        } catch (SQLException e) {
            throw new DAOException("An exception occurred during deleting auction.", e);
        }
    }

    @Override
    public boolean delete(Auction auction) throws DAOException {
        return delete(String.valueOf(auction.getId()));
    }

    @Override
    public long create(Auction auction) throws DAOException {
        LOGGER.info("Creating auction and inserting in database.");

        try {
            PreparedStatement statement = this.connection.prepareStatement(CREATE_AUCTION, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, auction.getLot().getId());
            statement.setLong(2, auction.getState().getValue());
            statement.setLong(3, auction.getType().getValue());
            statement.setLong(4, auction.getStartPrice());
            statement.setLong(5, auction.getDaysDurations());
            statement.setTimestamp(6, Timestamp.valueOf(auction.getStartDate()));
            statement.setTimestamp(7, Timestamp.valueOf(auction.getEndDate()));
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DAOException("An exception occurred during creating auction.", e);
        }
    }

    public long createVerifying(Auction auction) throws DAOException {
        LOGGER.info("Creating verifying auction and inserting in database.");

        try {
            PreparedStatement statement = this.connection.prepareStatement(CREATE_VERIFYING_AUCTION, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, auction.getLot().getId());
            statement.setLong(2, auction.getType().getValue());
            statement.setLong(3, auction.getStartPrice());
            statement.setLong(4, auction.getDaysDurations());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }

    @Override
    public Auction update(Auction auction) throws DAOException {
        LOGGER.info("Updating auction in database.");

        try {
            PreparedStatement statement = this.connection.prepareStatement(UPDATE_AUCTION, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, auction.getLot().getId());
            statement.setLong(2, auction.getState().getValue());
            statement.setLong(3, auction.getType().getValue());
            statement.setLong(4, auction.getDaysDurations());
            statement.setLong(5, auction.getStartPrice());
            statement.setTimestamp(6, Timestamp.valueOf(auction.getStartDate()));
            statement.setTimestamp(7, Timestamp.valueOf(auction.getEndDate()));
            statement.setLong(8, auction.getId());
            statement.executeUpdate();

            String bidId = String.valueOf(auction.getId());
            return findById(bidId);
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }

    public List<Auction> findAuctionsByState(int stateId) throws DAOException {
        LOGGER.info("Searching auction in database by state.");

        try {
            PreparedStatement statement = this.connection.prepareStatement(FIND_AUCTIONS_BY_STATE);
            statement.setInt(1, stateId);

            ResultSet rs = statement.executeQuery();

            AuctionCreator auctionCreator = new AuctionCreator();
            return auctionCreator.createListFromResultSet(rs);
        } catch (SQLException | EntityCreationException e) {
            throw new DAOException("And exception occurred during finding auctions by state.", e);
        }
    }

    public List<Auction> findAuctionByUserID(String userId) throws DAOException {
        LOGGER.info("Searching auction in database by user ID.");

        try {
            PreparedStatement statement = this.connection.prepareStatement(FIND_AUCTIONS_BY_USER);
            statement.setString(1, userId);

            ResultSet rs = statement.executeQuery();

            AuctionCreator auctionCreator = new AuctionCreator();
            return auctionCreator.createListFromResultSet(rs);
        } catch (SQLException | EntityCreationException e) {
            throw new DAOException("And exception occurred during finding auctions by user ID.", e);
        }
    }

    public void endOutdatedAuctions() throws DAOException {
        LOGGER.info("Ending outdated auction.");

        try (BidDAO bidDAO = new BidDAO()) {
            List<Auction> outdatedAuctions = findOutdatedAuctions();

            for (Auction auction : outdatedAuctions) {
                try {
                    PreparedStatement statement = this.connection.prepareStatement(END_OUTDATED_AUCTIONS);

                    if (bidDAO.hasAnyBid(auction.getId())) {
                        statement.setInt(1, AuctionState.SUCCESSFUL.getValue());
                    } else {
                        statement.setInt(1, AuctionState.UNSUCCESSFUL.getValue());
                    }
                    statement.setLong(2, auction.getId());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    throw new DAOException("And exception occurred during ending outdated auctions.", e);
                }
            }
        }
    }

    private List<Auction> findOutdatedAuctions() throws DAOException {
        LOGGER.info("Searching outdated auction in database.");

        try {
            PreparedStatement statement = this.connection.prepareStatement(FIND_OUTDATED_AUCTIONS);

            ResultSet rs = statement.executeQuery();

            AuctionCreator auctionCreator = new AuctionCreator();
            return auctionCreator.createListFromResultSet(rs);
        } catch (SQLException | EntityCreationException e) {
            throw new DAOException("And exception occurred during finding outdated auctions.", e);
        }
    }
}
