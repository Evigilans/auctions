package com.piankov.auctions.checker;

import com.piankov.auctions.dao.AuctionDAO;
import com.piankov.auctions.exception.ApplicationContextListenerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class AuctionChecker implements ServletContextListener {
    private static Logger LOGGER = LogManager.getLogger(AuctionChecker.class);

    private static final int TIMEOUT_MINUTES = 3;

    private void checkInformation() throws InterruptedException, ApplicationContextListenerException {
        while (true) {
            try (AuctionDAO auctionDAO = new AuctionDAO()) {
                LOGGER.info("Checking system information...");
                auctionDAO.endOutdatedAuctions();
                TimeUnit.MINUTES.sleep(TIMEOUT_MINUTES);
            } catch (SQLException e) {
                throw new ApplicationContextListenerException("An error occurred during execution a ContextListener's thread.");
            }
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.info("Creating ContextListener's thread for application...");
        Thread daemonChecker = new Thread(() -> {
            try {
                checkInformation();
            } catch (InterruptedException | ApplicationContextListenerException exception) {
                LOGGER.error("An error occurred during thread creation: " + exception.getMessage());
            }
        });
        daemonChecker.setDaemon(true);
        daemonChecker.start();
        LOGGER.info("Checking thread was successfully created and started!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
