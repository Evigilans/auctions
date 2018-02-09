package com.piankov.auctions.pool;

import com.mysql.jdbc.Driver;
import com.piankov.auctions.connection.ConnectionWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static final ReentrantLock LOCK = new ReentrantLock();

    private static ConnectionPool instance;

    private Deque<ConnectionWrapper> freeConnections;
    private Deque<ConnectionWrapper> workingConnections;


    //TODO: Что делать с этими параметрами???
    //////////////////////////////////////////////////////////////////////////////////////////////////

    private String url;
    private String username;
    private String password;
    private AtomicInteger poolSize = new AtomicInteger();

    private String getUrl() {
        return url;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    private String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private int getPoolSize() {
        return poolSize.get();
    }

    private void setPoolSize(int poolSize) {
        this.poolSize.set(poolSize);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////

    public static ConnectionPool getInstance() {
        if (instance == null) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instance.setUrl("jdbc:mysql://localhost:3306/auctions");
                    instance.setUsername("root");
                    instance.setPassword("root");
                    instance.setPoolSize(30);
                    instance.init();
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    private void init() {
        try {
            DriverManager.registerDriver(new Driver());
            freeConnections = new ArrayDeque<>(getPoolSize());
            workingConnections = new ArrayDeque<>(getPoolSize());
            for (int i = 0; i < poolSize.get(); i++) {
                ConnectionWrapper connection = new ConnectionWrapper(DriverManager.getConnection(getUrl(), getUsername(), getPassword()));
                freeConnections.add(connection);
            }
        } catch (SQLException e) {
            //LOGGER.error("");
        }
    }

    public ConnectionWrapper takeConnection() {
        //LOGGER.info("Trying to take connection.");
        ConnectionWrapper connection = null;
        connection = freeConnections.element();
        workingConnections.add(connection);
        LOGGER.info("Connection has successfully been given. Free connections left:" + freeConnections.size() + ", taken: " + workingConnections.size());
        return connection;
    }

    public void releaseConnection(ConnectionWrapper connection) {
        workingConnections.remove(connection);
        freeConnections.add(connection);
    }

    private void clearConnectionQueue() throws SQLException {
        ConnectionWrapper connection;
        while ((connection = freeConnections.poll()) != null) {
            connection.close();
        }
        while ((connection = workingConnections.poll()) != null) {
            connection.close();
        }
    }

    public void closePool() {
        if (instance != null) {
            try {
                instance.clearConnectionQueue();
                instance = null;
            } catch (SQLException e) {
                //LOGGER.error("");
            }
        }
    }
}
