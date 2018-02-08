package com.piankov.auctions.pool;

import com.mysql.jdbc.Driver;
import com.piankov.auctions.connection.ConnectionWrapper;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {
    private static ConnectionPool instance;

    private static final ReentrantLock LOCK = new ReentrantLock();

    private String url;
    private String username;
    private String password;
    private int poolSize;
    //private AtomicInteger poolSize = new AtomicInteger();

    private BlockingQueue<ConnectionWrapper> freeConnections;
    private BlockingQueue<ConnectionWrapper> workingConnections; //arraydeque

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

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

    public void init() {
        try {
            DriverManager.registerDriver(new Driver());
            freeConnections = new ArrayBlockingQueue<>(getPoolSize());
            workingConnections = new ArrayBlockingQueue<>(getPoolSize());
            for (int i = 0; i < poolSize; i++) {
                ConnectionWrapper connection = new ConnectionWrapper(DriverManager.getConnection(getUrl(), getUsername(), getPassword()));
                freeConnections.add(connection);
            }
        } catch (SQLException e) {
            System.out.println("1");
            System.err.println(e.getMessage());
        }
    }

    public ConnectionWrapper takeConnection() {
        ConnectionWrapper connection = null;
        System.out.println("Free: " + freeConnections.size());
        System.out.println("Taken: " + workingConnections.size());
        try {
            connection = freeConnections.take();
            workingConnections.put(connection);
        } catch (InterruptedException e) {
            System.out.println("2");
            System.err.println(e.getMessage());
        }
        return connection;
    }

    public void releaseConnection(ConnectionWrapper connection) {
        try {
            workingConnections.remove(connection);
            freeConnections.put(connection);
        } catch (InterruptedException e) {
            System.out.println("3");
            System.err.println(e.getMessage());
        }
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
                System.out.println("4");
                System.err.println(e.getMessage());
            }
        }
    }
}
