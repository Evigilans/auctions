package com.piankov.auctions.connection;

import com.mysql.jdbc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static final String RB_PATH = "resource.database";
    private static final String RB_URL = "database.url";
    private static final String RB_USER = "database.user";
    private static final String RB_PASSWORD = "database.password";
    private static final String RB_POOL_SIZE = "database.poolSize";

    private static final Lock LOCK = new ReentrantLock();

    private static ConnectionPool instance;

    private Deque<ConnectionWrapper> freeConnections;
    private Deque<ConnectionWrapper> workingConnections;
    private AtomicInteger poolSize = new AtomicInteger();

    public static ConnectionPool getInstance() {
        LOGGER.info("Getting instance of connection pool.");

        if (instance == null) {
            LOCK.lock();
            try {
                if (instance == null) {
                    LOGGER.info("Creating new instance of connection pool.");

                    ResourceBundle resourceBundle = ResourceBundle.getBundle(RB_PATH);

                    String url = resourceBundle.getString(RB_URL);
                    String user = resourceBundle.getString(RB_USER);
                    String password = resourceBundle.getString(RB_PASSWORD);
                    String poolSizeString = resourceBundle.getString(RB_POOL_SIZE);

                    instance = new ConnectionPool();
                    instance.init(url, user, password, poolSizeString);
                }
            } finally {
                LOCK.unlock();
            }
        }

        return instance;
    }

    private void init(String url, String user, String password, String poolSizeString) {
        LOGGER.info("Initializing connection pool.");

        try {
            LOGGER.info("Registering drivers and initializing arrays.");
            DriverManager.registerDriver(new Driver());
            setPoolSize(Integer.parseInt(poolSizeString));

            freeConnections = new ArrayDeque<>(poolSize.get());
            workingConnections = new ArrayDeque<>(poolSize.get());

            for (int i = 0; i < this.poolSize.get(); i++) {
                ConnectionWrapper connection = new ConnectionWrapper(DriverManager.getConnection(url, user, password));
                freeConnections.add(connection);
            }
        } catch (SQLException e) {
            LOGGER.error("And error occurred during connection pool initializing.", e);
        }
    }

    public ConnectionWrapper takeConnection() {
        LOGGER.info("Taking free connection.");

        ConnectionWrapper connection = freeConnections.remove();
        workingConnections.add(connection);

        LOGGER.info("Connection has successfully been taken. Free connections left: " + freeConnections.size() + ", taken: " + workingConnections.size());
        return connection;
    }

    public void releaseConnection(ConnectionWrapper connection) {
        LOGGER.info("Releasing taken connection.");

        workingConnections.remove(connection);
        freeConnections.add(connection);

        LOGGER.info("Connection has successfully been release. Free connections left: " + freeConnections.size() + ", taken: " + workingConnections.size());
    }

    private void clearConnectionDeque() throws SQLException {
        LOGGER.info("Clearing connection deque.");

        ConnectionWrapper connection;
        while ((connection = freeConnections.poll()) != null) {
            LOGGER.info("Closing free connections.");
            connection.close();
        }
        while ((connection = workingConnections.poll()) != null) {
            LOGGER.info("Closing working connections.");
            connection.close();
        }

        LOGGER.info("Connection deque was successfully cleared.");
    }

    public void closePool() {
        LOGGER.info("Closing connection pool.");

        if (instance != null) {
            LOCK.lock();
            try {
                if (instance != null) {
                    instance.clearConnectionDeque();
                    instance = null;
                }
            } catch (SQLException e) {
                LOGGER.error("And error occurred during connection pool closing.", e);
            } finally {
                LOCK.unlock();
            }
        }

        LOGGER.info("Connection pool successfully closed.");
    }

    private int getPoolSize() {
        return poolSize.get();
    }

    private void setPoolSize(int poolSize) {
        this.poolSize.set(poolSize);
    }
}
