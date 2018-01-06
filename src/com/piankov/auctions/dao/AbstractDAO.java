package com.piankov.auctions.dao;


import com.piankov.auctions.connection.ConnectionWrapper;
import com.piankov.auctions.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO {
    private ConnectionPool pool;

    public ConnectionPool getPool() {
        return pool;
    }

    public void setPool(ConnectionPool pool) {
        this.pool = pool;
    }

    public ConnectionWrapper getConnection() {
        return getPool().takeConnection();
    }

    public void returnConnection(ConnectionWrapper connection) {
        getPool().releaseConnection(connection);
    }

    public void closePreparedStatement(PreparedStatement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    public void closeStatement(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }
}
