package com.piankov.auctions.pool;

import java.util.concurrent.atomic.AtomicInteger;

public class DataBaseAccessProvider {
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
}
