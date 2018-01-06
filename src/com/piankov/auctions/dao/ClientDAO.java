package com.piankov.auctions.dao;

import com.piankov.auctions.client.Client;
import com.piankov.auctions.connection.ConnectionWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO extends AbstractDAO {
    private static final String INSERT_INFO = "";
    private static final String ID_COLUMN = "";

    public Client insertClient(Client client) {
        PreparedStatement statement = null;
        ConnectionWrapper connection = null;
        long id;
        String[] columns = {ID_COLUMN};
        try {
            try {
                String QUERY = "INSERT INTO test VALUES (NULL, \'" + client.getLogin() + "\', \'" +
                                client.getPasswordHash() + "\', \'" + client.getName() + "\', \'" +
                                client.getEmail() + "\', \'" + client.getBalance() + "\')";
                connection = getConnection();
                statement = connection.prepareStatement(QUERY, columns);
                setStatementFields(statement, client);
                statement.executeUpdate();
                ResultSet keys = statement.getGeneratedKeys();
                while (keys.next()) {
                    //??
                    id = keys.getLong(1);
                    client.setId(id);
                }
            } finally {
                closePreparedStatement(statement);
                returnConnection(connection);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return client;
    }

    private void setStatementFields(PreparedStatement statement, Client client) {

    }
}
