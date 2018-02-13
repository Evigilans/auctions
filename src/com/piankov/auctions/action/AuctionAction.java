package com.piankov.auctions.action;

import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.creator.AuctionCreator;
import com.piankov.auctions.creator.BidCreator;
import com.piankov.auctions.dao.AuctionDAO;
import com.piankov.auctions.dao.BidDAO;
import com.piankov.auctions.entity.*;
import com.piankov.auctions.exception.DAOException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class AuctionAction {
    public void makeBid(Map<String, String[]> parameterMap, User user) {
        try (BidDAO bidDAO = new BidDAO()) {
            BidCreator bidCreator = new BidCreator();
            Bid bid = bidCreator.buildEntityFromMap(parameterMap, user);

            long generatedId = bidDAO.create(bid);
            bid.setId(generatedId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }



    public Auction createActiveAuction(Map<String, String[]> parameterMap, User user) {
        Auction auction = null;

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            LotAction lotAction = new LotAction();
            Lot lot = lotAction.createLot(parameterMap, user);

            AuctionCreator auctionCreator = new AuctionCreator();
            auction = auctionCreator.buildEntityFromMap(parameterMap, lot);

            auction.setState(AuctionState.IN_PROGRESS);
            auction.setStartDate(LocalDateTime.now());
            auction.setEndDate(auction.getStartDate().plusDays(auction.getDaysDurations()));

            long generatedId = auctionDAO.create(auction);
            auction.setId(generatedId);

            return auction;
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return auction;
    }

    public Auction createVerifyingAuction(Map<String, String[]> parameterMap, User user) {
        Auction auction = null;

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            LotAction lotAction = new LotAction();
            Lot lot = lotAction.createLot(parameterMap, user);

            AuctionCreator auctionCreator = new AuctionCreator();
            auction = auctionCreator.buildEntityFromMap(parameterMap, lot);

            auction.setState(AuctionState.ON_VERIFICATION);

            long generatedId = auctionDAO.createVerifying(auction);
            auction.setId(generatedId);

            return auction;
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return auction;
    }

    public Auction findAuctionById(String auctionId) {
        Auction auction = null;

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            auction = auctionDAO.findById(auctionId);
            System.out.println(auction);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return auction;
    }

    public Auction verifyAuction(Auction auction) {
        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            auction.setState(AuctionState.IN_PROGRESS);
            auction.setStartDate(LocalDateTime.now());
            auction.setEndDate(auction.getStartDate().plusDays(auction.getDaysDurations()));

            return auctionDAO.update(auction);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return auction;
    }

    public List<Auction> findAllAuctionsByState(int stateId) {
        List<Auction> auctions = null;

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            auctions = auctionDAO.findAuctionsByState(stateId);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return auctions;
    }

    public Auction updateLot(String auctionId, Map<String, String[]> parameterMap) {
        return null;
    }

    public Auction updateAuction(Auction auction, Map<String, String[]> parameterMap) {
        try (AuctionDAO auctionDAO = new AuctionDAO()) {
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
            e.printStackTrace();
        }

        return null;
    }

    public Auction updateBid(String bidId, Map<String, String[]> parameterMap) {
        return null;
    }

    public Auction withdrawAuction(Auction auction) {
        return null;
    }

    public Auction findBidById(String bidID) {
        return null;
    }
}
