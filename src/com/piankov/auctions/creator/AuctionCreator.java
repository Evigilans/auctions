package com.piankov.auctions.creator;

import com.piankov.auctions.dao.BidDAO;
import com.piankov.auctions.dao.LotDAO;
import com.piankov.auctions.entity.Auction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuctionCreator extends AbstractCreator<Auction> {
    private static final String ID_ROW = "ID";
    private static final String LOT_ID_ROW = "LOT_ID";
    private static final String AUCTION_STATE_ID_ROW = "AUCTION_STATE_ID";
    private static final String AUCTION_TYPE_ID_ROW = "AUCTION_TYPE_ID";
    private static final String START_DATE_ROW = "START_DATE";
    private static final String END_DATE_ROW = "END_DATE";

    @Override
    public Auction buildEntityFromMap(Map<String, String> parameters) {
        return null;
    }

    @Override
    public Auction buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        LotDAO lotDAO = new LotDAO();
        BidDAO bidDAO = new BidDAO();
        if (resultSet.next()) {
            Auction auction = new Auction();
            String auctionId = resultSet.getString(ID_ROW);
            auction.setId(Long.parseLong(auctionId));
            auction.setLot(lotDAO.findByAuctionId(auctionId));
            //auction.setStateId(rs.getInt(AUCTION_STATE_ID_ROW));
            //auction.setTypeId(rs.getInt(AUCTION_TYPE_ID_ROW));
            auction.setCurrentMaximalBid(bidDAO.findMaxBidByAuctionId(auctionId));
            //TODO: DATES
            return auction;
        }
        return null;
    }

    @Override
    public List<Auction> buildListFromResultSet(ResultSet resultSet) throws SQLException {
        LotDAO lotDAO = new LotDAO();
        BidDAO bidDAO = new BidDAO();

        List<Auction> auctions = new ArrayList<>();
        while (resultSet.next()) {
            Auction auction = new Auction();
            String auctionId = resultSet.getString(ID_ROW);
            auction.setId(Long.parseLong(auctionId));
            auction.setLot(lotDAO.findByAuctionId(auctionId));
            //auction.setStateId(rs.getInt(AUCTION_STATE_ID_ROW));
            //auction.setTypeId(rs.getInt(AUCTION_TYPE_ID_ROW));
            auction.setCurrentMaximalBid(bidDAO.findMaxBidByAuctionId(auctionId));
            //TODO: DATES
            auctions.add(auction);
        }
        return auctions;
    }
}
