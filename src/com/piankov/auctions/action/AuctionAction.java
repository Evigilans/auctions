package com.piankov.auctions.action;

import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.creator.AuctionCreator;
import com.piankov.auctions.creator.BidCreator;
import com.piankov.auctions.dao.AuctionDAO;
import com.piankov.auctions.dao.BidDAO;
import com.piankov.auctions.dao.LotDAO;
import com.piankov.auctions.entity.*;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.DAOException;
import com.piankov.auctions.validator.AuctionValidator;
import com.piankov.auctions.validator.LotValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class AuctionAction {
    /**
     *
     */
    private static Logger LOGGER = LogManager.getLogger(AuctionAction.class);

    /**
     * @param parameterMap
     * @param user
     * @throws ActionPerformingException
     */
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

    public List<Auction> findUserAuctions(String userId) throws ActionPerformingException {
        LOGGER.info("Performing 'Find User Auctions' action.");

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            return auctionDAO.findAuctionByUserID(userId);
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

        try (AuctionDAO auctionDAO = new AuctionDAO();
             LotDAO lotDAO = new LotDAO()) {
            LOGGER.info("Resetting fields and updating in database.");
            Lot lot = auction.getLot();
            LotValidator lotValidator = new LotValidator();
            AuctionValidator auctionValidator = new AuctionValidator();

            String lotName = parameterMap.get(ParameterConstant.PARAMETER_LOT_NAME)[0];
            if (lotName != null && lotValidator.validateName(lotName)) {
                lot.setName(lotName);
            }

            String lotDescription = parameterMap.get(ParameterConstant.PARAMETER_LOT_DESCRIPTION)[0];
            if (lotDescription != null && lotValidator.validateDescription(lotDescription)) {
                lot.setDescription(lotDescription);
            }

            String startPrice = parameterMap.get(ParameterConstant.PARAMETER_START_PRICE)[0];
            if (startPrice != null && auctionValidator.isPositiveInteger(startPrice)) {
                int newStartPrice = Integer.parseInt(startPrice);
                auction.setStartPrice(newStartPrice);
            }

            String days = parameterMap.get(ParameterConstant.PARAMETER_DAYS)[0];
            if (days != null && auctionValidator.isPositiveInteger(days)) {
                int newDays = Integer.parseInt(days);
                auction.setDaysDurations(newDays);
            }

            lotDAO.update(lot);
            return auctionDAO.update(auction);
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Update Auction' action.", e);
        }
    }

    public Auction withdrawAuction(Auction auction) throws ActionPerformingException {
        LOGGER.info("Performing 'Withdraw Auction' action.");

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            LOGGER.info("Resetting state and updating in database.");

            auction.setState(AuctionState.WITHDRAW_FROM_SALES);

            return auctionDAO.update(auction);
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Withdraw Auction' action.", e);
        }
    }

    public void deleteBid(Auction auction) throws ActionPerformingException {
        LOGGER.info("Performing 'Delete Bid' action.");

        try (BidDAO bidDAO = new BidDAO()) {
            LOGGER.info("Deleting bid from database.");

            bidDAO.delete(String.valueOf(auction.getCurrentMaximalBid().getId()));
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Delete Bid' action.", e);
        }
    }
}
