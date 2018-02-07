package com.piankov.auctions.dao;

import com.piankov.auctions.entity.Lot;
import com.piankov.auctions.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LotDAO extends AbstractDAO<Lot> {
    public LotDAO() {
        this.connection = ConnectionPool.getInstance().takeConnection();
    }

    @Override
    public List<Lot> findAll() throws SQLException {
        List<Lot> lots = new ArrayList<>();
        String query = "SELECT * FROM LOT";
        PreparedStatement statement = this.connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Lot lot = new Lot();
            lot.setId(rs.getInt("ID"));
            lot.setOwnerId(rs.getInt("OWNER_ID"));
            lot.setStartPrice(rs.getInt("START_PRICE"));
            lot.setName(rs.getString("NAME"));
            lot.setDescription(rs.getString("DESCRIPTION"));
            lots.add(lot);
        }
        return lots;
    }

    @Override
    public Lot findById(String id) throws SQLException {
        String query = "SELECT * FROM LOT WHERE LOT.ID = ?";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            Lot lot = new Lot();
            lot.setId(rs.getInt("ID"));
            lot.setOwnerId(rs.getInt("OWNER_ID"));
            lot.setStartPrice(rs.getInt("START_PRICE"));
            lot.setName(rs.getString("NAME"));
            lot.setDescription(rs.getString("DESCRIPTION"));
            return lot;
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean delete(Lot entity) {
        return false;
    }

    @Override
    public long create(Lot lot) throws SQLException {
        String query = "INSERT INTO LOT (OWNER_ID, START_PRICE, NAME, DESCRIPTION) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, String.valueOf(lot.getOwnerId()));
        statement.setString(2, String.valueOf(lot.getStartPrice()));
        statement.setString(3, lot.getName());
        statement.setString(4, lot.getDescription());
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getLong(1);
    }

    @Override
    public Lot update(Lot entity) {
        return null;
    }

    public Lot findByAuctionId(String auctionId) throws SQLException {
        String query = "SELECT * FROM LOT WHERE ID = (SELECT LOT_ID FROM AUCTION WHERE ID = ?)";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1, auctionId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            Lot lot = new Lot();
            lot.setId(rs.getInt("ID"));
            lot.setOwnerId(rs.getInt("OWNER_ID"));
            lot.setStartPrice(rs.getInt("START_PRICE"));
            lot.setName(rs.getString("NAME"));
            lot.setDescription(rs.getString("DESCRIPTION"));
            return lot;
        }
        return null;
    }
}
