package com.piankov.auctions.dao;

import com.piankov.auctions.creator.LotCreator;
import com.piankov.auctions.entity.Lot;
import com.piankov.auctions.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class LotDAO extends AbstractDAO<Lot> {
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
        try {
            PreparedStatement statement = this.connection.prepareStatement(FIND_ALL_LOTS);
            ResultSet rs = statement.executeQuery();
            LotCreator lotCreator = new LotCreator();
            return lotCreator.buildListFromResultSet(rs);
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }

    @Override
    public Lot findById(String id) throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(FIND_LOT_BY_ID);

            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();

            LotCreator lotCreator = new LotCreator();
            return lotCreator.buildEntityFromResultSet(rs);
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }

    @Override
    public boolean delete(String id) throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(DELETE_LOT_BY_ID, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, id);

            return statement.execute();
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }

    @Override
    public boolean delete(Lot lot) throws DAOException {
        return delete(String.valueOf(lot.getId()));
    }

    @Override
    public long create(Lot lot) throws DAOException {
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
            throw new DAOException("", e);
        }
    }

    @Override
    public Lot update(Lot lot) throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(UPDATE_LOT, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, lot.getOwner().getId());
            statement.setString(2, lot.getName());
            statement.setString(3, lot.getDescription());
            statement.executeUpdate();

            String bidId = String.valueOf(lot.getId());
            return findById(bidId);
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }

    public Lot findByAuctionId(String auctionId) throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(FIND_LOT_BY_AUCTION_ID);

            statement.setString(1, auctionId);
            ResultSet rs = statement.executeQuery();

            LotCreator lotCreator = new LotCreator();
            return lotCreator.buildEntityFromResultSet(rs);
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }
}
