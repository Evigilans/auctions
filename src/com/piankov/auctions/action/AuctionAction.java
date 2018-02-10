package com.piankov.auctions.action;

import com.piankov.auctions.creator.AuctionCreator;
import com.piankov.auctions.creator.BidCreator;
import com.piankov.auctions.dao.AuctionDAO;
import com.piankov.auctions.dao.BidDAO;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.Bid;
import com.piankov.auctions.entity.User;

import java.sql.SQLException;
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

    public Auction createAuction(Map<String, String[]> parameterMap, User user) {
        Auction auction;

        try (AuctionDAO auctionDAO = new AuctionDAO()) {
            AuctionCreator auctionCreator = new AuctionCreator();
            auction = auctionCreator.buildEntityFromMap(parameterMap, user);

            long generatedId = auctionDAO.create(auction);
            auction.setId(generatedId);

            return auction;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
