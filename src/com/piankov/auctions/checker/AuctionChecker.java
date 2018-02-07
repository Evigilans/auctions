package com.piankov.auctions.checker;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.TimeUnit;

public class AuctionChecker implements ServletContextListener {
    public void check() throws InterruptedException {
        while (true) {
            System.out.println("Checking...");
            TimeUnit.MINUTES.sleep(1);
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Thread daemon = new  Thread(() -> {
            try {
                check();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        daemon.setDaemon(true);
        daemon.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
