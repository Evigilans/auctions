package com.piankov.auctions.dao;


import com.mysql.jdbc.Statement;
import com.piankov.auctions.connection.ConnectionWrapper;
import com.piankov.auctions.entity.Entity;
import com.piankov.auctions.pool.ConnectionPool;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T extends Entity> {
    protected ConnectionWrapper connection;

    public AbstractDAO() {
        this.connection = ConnectionPool.getInstance().takeConnection();
    }

    public abstract List<T> findAll() throws SQLException;

    public abstract T findById(String id) throws SQLException;

    public abstract boolean delete(String id) throws SQLException;

    public abstract boolean delete(T entity) throws SQLException;

    public abstract long create(T entity) throws SQLException;

    public abstract T update(T entity) throws SQLException;

    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            // лог о невозможности закрытия Statement
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // генерация исключения, т.к. нарушается работа пула
        }
    }
}
