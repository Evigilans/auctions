package com.piankov.auctions.connection;

import com.piankov.auctions.pool.ConnectionPool;

public class ConnectionAction {
    public ConnectionPool createConnectionPool() {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.setUrl("jdbc:mysql://localhost:3306/AuctionServer");
        pool.setUsername("root");
        pool.setPassword("root");
        pool.setPoolSize(30);
        pool.init();

        return pool;
    }
}
