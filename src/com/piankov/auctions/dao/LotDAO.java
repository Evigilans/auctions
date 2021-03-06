package com.piankov.auctions.dao;

import com.piankov.auctions.creator.LotCreator;
import com.piankov.auctions.entity.Lot;
import com.piankov.auctions.exception.DAOException;
import com.piankov.auctions.exception.EntityCreationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class LotDAO extends AbstractDAO<Lot> {
    private static final Logger LOGGER = LogManager.getLogger(LotDAO.class);

    private static final String FIND_ALL_LOTS = "SELECT * FROM LOT";
    private static final String FIND_LOT_BY_ID = "SELECT * FROM LOT WHERE ID = ?";
    private static final String DELETE_LOT_BY_ID = "DELETE FROM LOT WHERE ID = ?";
    private static final String UPDATE_LOT = "UPDATE LOT SET OWNER_ID = ?, NAME = ?, DESCRIPTION = ? WHERE ID = ?";
    private static final String CREATE_LOT = "INSERT INTO LOT (OWNER_ID, NAME, DESCRIPTION) VALUES (?, ?, ?)";
    private static final String FIND_LOT_BY_AUCTION_ID = "SELECT * FROM LOT WHERE ID = (SELECT LOT_ID FROM AUCTION WHERE ID = ?)";

    public LotDAO() {
        super();
    }

    @Override
    public List<Lot> findAll() throws DAOException {
        LOGGER.info("Searching all lots in database.");

        try {
            PreparedStatement statement = this.connection.prepareStatement(FIND_ALL_LOTS);
            ResultSet rs = statement.executeQuery();
            LotCreator lotCreator = new LotCreator();
            return lotCreator.createListFromResultSet(rs);
        } catch (SQLException | EntityCreationException e) {
            throw new DAOException("And exception occurred during finding all lost.", e);
        }
    }

    @Override
    public Lot findById(String id) throws DAOException {
        LOGGER.info("Searching lot in database by ID.");

        try {
            LotCreator lotCreator = new LotCreator();
            PreparedStatement statement = this.connection.prepareStatement(FIND_LOT_BY_ID);

            statement.setString(1, id);

            ResultSet rs = statement.executeQuery();
            return lotCreator.createEntityFromResultSet(rs);
        } catch (SQLException | EntityCreationException e) {
            throw new DAOException("And exception occurred during finding lot by ID.", e);
        }
    }

    @Override
    public boolean delete(String id) throws DAOException {
        LOGGER.info("Deleting lot from database.");

        try {
            PreparedStatement statement = this.connection.prepareStatement(DELETE_LOT_BY_ID, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, id);

            return statement.execute();
        } catch (SQLException e) {
            throw new DAOException("And exception occurred during deleting lot.", e);
        }
    }

    @Override
    public boolean delete(Lot lot) throws DAOException {
        return delete(String.valueOf(lot.getId()));
    }

    @Override
    public long create(Lot lot) throws DAOException {
        LOGGER.info("Creating lot and inserting in database.");

        try {
            PreparedStatement statement = this.connection.prepareStatement(CREATE_LOT, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, lot.getOwner().getId());
            statement.setString(2, lot.getName());
            statement.setString(3, lot.getDescription());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DAOException("And exception occurred during creating lot.", e);
        }
    }

    @Override
    public Lot update(Lot lot) throws DAOException {
        LOGGER.info("Updating lot in database.");

        try {
            PreparedStatement statement = this.connection.prepareStatement(UPDATE_LOT, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, lot.getOwner().getId());
            statement.setString(2, lot.getName());
            statement.setString(3, lot.getDescription());
            statement.setLong(4, lot.getId());
            statement.executeUpdate();

            return findById(String.valueOf(lot.getId()));
        } catch (SQLException e) {
            throw new DAOException("And exception occurred during updating lot.", e);
        }
    }

    public Lot findByAuctionId(String auctionId) throws DAOException {
        LOGGER.info("Searching lot in database by auction ID.");

        try {
            PreparedStatement statement = this.connection.prepareStatement(FIND_LOT_BY_AUCTION_ID);

            statement.setString(1, auctionId);
            ResultSet rs = statement.executeQuery();

            LotCreator lotCreator = new LotCreator();
            return lotCreator.createEntityFromResultSet(rs);
        } catch (SQLException | EntityCreationException e) {
            throw new DAOException("And exception occurred during finding lot by auction ID.", e);
        }
    }
}
