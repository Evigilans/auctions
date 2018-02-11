package com.piankov.auctions.action;

import com.piankov.auctions.creator.AuctionCreator;
import com.piankov.auctions.creator.BidCreator;
import com.piankov.auctions.creator.LotCreator;
import com.piankov.auctions.dao.AuctionDAO;
import com.piankov.auctions.dao.BidDAO;
import com.piankov.auctions.dao.LotDAO;
import com.piankov.auctions.entity.*;

import java.sql.SQLException;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Lot createLot(Map<String, String[]> parameterMap, User user) {
        Lot lot = null;

        try (LotDAO lotDAO = new LotDAO()) {
            LotCreator lotCreator = new LotCreator();
            lot = lotCreator.buildEntityFromMap(parameterMap, user);

            long lotId = lotDAO.create(lot);
            lot.setId(lotId);

            return lot;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lot;
    }

    public Auction createActiveAuction(Map<String, String[]> parameterMap, User user) {
        Auction auction = null;

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            Lot lot = createLot(parameterMap, user);

            AuctionCreator auctionCreator = new AuctionCreator();
            auction = auctionCreator.buildEntityFromMap(parameterMap, lot);

            auction.setState(AuctionState.IN_PROGRESS);
            auction.setStartDate(LocalDateTime.now());
            auction.setEndDate(auction.getStartDate().plusDays(auction.getDaysDurations()));

            long generatedId = auctionDAO.create(auction);
            auction.setId(generatedId);

            return auction;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auction;
    }

    public Auction createVerifyingAuction(Map<String, String[]> parameterMap, User user) {
        Auction auction = null;

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            Lot lot = createLot(parameterMap, user);

            AuctionCreator auctionCreator = new AuctionCreator();
            auction = auctionCreator.buildEntityFromMap(parameterMap, lot);

            auction.setState(AuctionState.ON_VERIFICATION);

            long generatedId = auctionDAO.createVerifying(auction);
            auction.setId(generatedId);

            return auction;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auction;
    }

    public Auction findAuctionById(String auctionId) {
        Auction auction = null;

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            auction = auctionDAO.findById(auctionId);
            System.out.println(auction);
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auction;
    }

    public List<Auction> findAllAuctionsByState(int stateId) {
        List<Auction> auctions = null;

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            auctions = auctionDAO.findAuctionsByState(stateId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auctions;
    }
}
