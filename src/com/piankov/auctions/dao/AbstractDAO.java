package com.piankov.auctions.dao;


import com.mysql.jdbc.Statement;
import com.piankov.auctions.connection.ConnectionWrapper;
import com.piankov.auctions.entity.Entity;
import com.piankov.auctions.connection.ConnectionPool;
import com.piankov.auctions.exception.DAOException;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T extends Entity> implements Closeable {
    protected ConnectionWrapper connection;

    AbstractDAO() {
        this.connection = ConnectionPool.getInstance().takeConnection();
    }

    public abstract List<T> findAll() throws DAOException;

    public abstract T findById(String id) throws DAOException;

    public abstract boolean delete(String id) throws DAOException;

    public abstract boolean delete(T entity) throws DAOException;

    public abstract long create(T entity) throws DAOException;

    public abstract T update(T entity) throws DAOException;

    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            // лог о невозможности закрытия Statement
        }
    }

    @Override
    public void close() {
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}
