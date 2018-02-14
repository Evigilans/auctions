package com.piankov.auctions.action;

import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.creator.AuctionCreator;
import com.piankov.auctions.creator.BidCreator;
import com.piankov.auctions.dao.AuctionDAO;
import com.piankov.auctions.dao.BidDAO;
import com.piankov.auctions.entity.*;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class AuctionAction {
    private static Logger LOGGER = LogManager.getLogger(AuctionAction.class);

    public void makeBid(Map<String, String[]> parameterMap, User user) throws ActionPerformingException {
        LOGGER.info("Performing 'Make Bid' action.");

        try (BidDAO bidDAO = new BidDAO()) {
            LOGGER.info("Creating and inserting bid in database.");
            BidCreator bidCreator = new BidCreator();
            Bid bid = bidCreator.createEntityFromMap(parameterMap, user);

            long generatedId = bidDAO.create(bid);
            bid.setId(generatedId);
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Make Bid' action.", e);
        }
    }

    public Auction createActiveAuction(Map<String, String[]> parameterMap, User user) throws ActionPerformingException {
        LOGGER.info("Performing 'Create Active Auction' action.");

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            LOGGER.info("Creating and inserting active auction in database.");
            LotAction lotAction = new LotAction();
            Lot lot = lotAction.createLot(parameterMap, user);

            AuctionCreator auctionCreator = new AuctionCreator();
            Auction auction = auctionCreator.createEntityFromMap(parameterMap, lot);

            auction.setState(AuctionState.IN_PROGRESS);
            auction.setStartDate(LocalDateTime.now());
            auction.setEndDate(auction.getStartDate().plusDays(auction.getDaysDurations()));

            long generatedId = auctionDAO.create(auction);
            auction.setId(generatedId);

            return auction;
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Create Active Auction' action.", e);
        }
    }

    public Auction createVerifyingAuction(Map<String, String[]> parameterMap, User user) throws ActionPerformingException {
        LOGGER.info("Performing 'Create Verifying auction' action.");

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            LOGGER.info("Creating and inserting verifying auction in database.");
            LotAction lotAction = new LotAction();
            Lot lot = lotAction.createLot(parameterMap, user);

            AuctionCreator auctionCreator = new AuctionCreator();
            Auction auction = auctionCreator.createEntityFromMap(parameterMap, lot);

            auction.setState(AuctionState.ON_VERIFICATION);

            long generatedId = auctionDAO.createVerifying(auction);
            auction.setId(generatedId);

            return auction;
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Find User' action.", e);
        }
    }

    public Auction findAuctionById(String auctionId) throws ActionPerformingException {
        LOGGER.info("Performing 'Find Auction By ID' action.");

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            return auctionDAO.findById(auctionId);
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Find Auction By ID' action.", e);
        }
    }

    public Auction verifyAuction(Auction auction) throws ActionPerformingException {
        LOGGER.info("Performing 'Verify Auction' action.");

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            LOGGER.info("Finding and updating auction in database.");
            auction.setState(AuctionState.IN_PROGRESS);
            auction.setStartDate(LocalDateTime.now());
            auction.setEndDate(auction.getStartDate().plusDays(auction.getDaysDurations()));

            return auctionDAO.update(auction);
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Verify Auction' action.", e);
        }
    }

    public List<Auction> findAllAuctionsByState(int stateId) throws ActionPerformingException {
        LOGGER.info("Performing 'Find All Auction By ID' action.");

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            LOGGER.info("Finding in database and creating auction list.");

            return auctionDAO.findAuctionsByState(stateId);
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Find All Auction By ID' action.", e);
        }
    }

    public Auction updateAuction(Auction auction, Map<String, String[]> parameterMap) throws ActionPerformingException {
        LOGGER.info("Performing 'Update Auction' action.");

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            LOGGER.info("Resetting fields and updating in database.");

            String startPrice = parameterMap.get(ParameterConstant.PARAMETER_START_PRICE)[0];
            if (startPrice != null) {
                //auction.setName(startPrice);
            }

            String days = parameterMap.get(ParameterConstant.PARAMETER_DAYS)[0];
            if (days != null) {
                auction.setDaysDurations(Integer.parseInt(days));
            }

            return auctionDAO.update(auction);
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Update Auction' action.", e);
        }
    }

    public Auction updateLot(String auctionId, Map<String, String[]> parameterMap) {
        return null;
    }

    public Auction withdrawAuction(Auction auction) {
        return null;
    }

    public Auction findBidById(String bidID) {
        return null;
    }
}
