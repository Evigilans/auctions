package com.piankov.auctions.dao;

import com.piankov.auctions.entity.Client;
import com.piankov.auctions.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClientDAO extends AbstractDAO<Client> {
    private static final String INSERT_INFO = "";
    private static final String ID_COLUMN = "";

    public ClientDAO() {
        this.connection = ConnectionPool.getInstance().takeConnection();
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public Client findById(String id) throws SQLException {
        String query = "SELECT NAME, EMAIL, CATEGORY FROM CLIENT WHERE ID = ?";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            Client client = new Client();
            client.setName(rs.getString("NAME"));
            client.setEmail(rs.getString("EMAIL"));
            client.setCategory(rs.getInt("CATEGORY"));
            return client;
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean delete(Client entity) {
        return false;
    }

    @Override
    public long create(Client client) throws SQLException {
        String query = "INSERT INTO CLIENT (LOGIN, PASSWORD_HASH, NAME, EMAIL) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, client.getLogin());
        statement.setString(2, client.getPasswordHash());
        statement.setString(3, client.getName());
        statement.setString(4, client.getEmail());
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getLong(1);
    }

    @Override
    public Client update(Client entity) {
        return null;
    }

    public Client findClient(String clientName, String password) throws SQLException {
        String query = "SELECT ID, LOGIN, NAME, EMAIL, BALANCE, CATEGORY FROM CLIENT WHERE LOGIN = ? AND PASSWORD_HASH = ?";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1, clientName);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            Client client = new Client();
            client.setId(rs.getInt("ID"));
            client.setLogin(rs.getString("LOGIN"));
            client.setName(rs.getString("NAME"));
            client.setEmail(rs.getString("EMAIL"));
            client.setBalance(rs.getInt("BALANCE"));
            client.setCategory(rs.getInt("CATEGORY"));
            return client;
        }
        return null;
    }

    private void setStatementFields(PreparedStatement statement, Client client) {

    }
}
