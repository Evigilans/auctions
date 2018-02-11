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
        if (instance == null) {
            LOCK.lock();
            try {
                if (instance == null) {
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
        try {
            DriverManager.registerDriver(new Driver());
            setPoolSize(Integer.parseInt(poolSizeString));

            freeConnections = new ArrayDeque<>(poolSize.get());
            workingConnections = new ArrayDeque<>(poolSize.get());

            for (int i = 0; i < this.poolSize.get(); i++) {
                ConnectionWrapper connection = new ConnectionWrapper(DriverManager.getConnection(url, user, password));
                freeConnections.add(connection);
            }
        } catch (SQLException e) {
            //LOGGER.error("");
        }
    }

    public ConnectionWrapper takeConnection() {
        //LOGGER.info("Trying to take connection.");

        ConnectionWrapper connection = freeConnections.remove();
        workingConnections.add(connection);

        LOGGER.info("Connection has successfully been taken. Free connections left: " + freeConnections.size() + ", taken: " + workingConnections.size());
        return connection;
    }

    public void releaseConnection(ConnectionWrapper connection) {
        workingConnections.remove(connection);
        freeConnections.add(connection);
    }

    private void clearConnectionDeque() throws SQLException {
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
            LOCK.lock();
            try {
                if (instance != null) {
                    instance.clearConnectionDeque();
                    instance = null;
                }
            } catch (SQLException e) {
                //LOGGER.error("");
            } finally {
                LOCK.unlock();
            }
        }
    }

    private int getPoolSize() {
        return poolSize.get();
    }

    private void setPoolSize(int poolSize) {
        this.poolSize.set(poolSize);
    }
}
