package com.piankov.auctions.creator;

import com.piankov.auctions.dao.BidDAO;
import com.piankov.auctions.dao.LotDAO;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.AuctionState;
import com.piankov.auctions.entity.AuctionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuctionCreator extends AbstractCreator<Auction> {
    private static final Logger LOGGER = LogManager.getLogger(AuctionCreator.class);

    private static final String ID = "ID";
    private static final String LOT_ID = "LOT_ID";
    private static final String AUCTION_STATE_ID = "AUCTION_STATE_ID";
    private static final String AUCTION_TYPE_ID = "AUCTION_TYPE_ID";
    private static final String DAYS_DURATION = "DAYS_DURATION";
    private static final String START_DATE = "START_DATE";
    private static final String END_DATE = "END_DATE";

    @Override
    public Auction buildEntityFromMap(Map<String, String[]> parameterMap) {
        return null;
    }

    @Override
    public Auction buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        try (LotDAO lotDAO = new LotDAO();
             BidDAO bidDAO = new BidDAO()) {

            if (resultSet.next()) {
                Auction auction = new Auction();

                //TODO: из базы данных или resultSet???
                String auctionId = resultSet.getString(ID);
                auction.setId(Long.parseLong(auctionId));
                auction.setLot(lotDAO.findByAuctionId(auctionId));
                auction.setState(AuctionState.getStateFromValue(resultSet.getInt(AUCTION_STATE_ID)));
                auction.setType(AuctionType.getTypeFromValue(resultSet.getInt(AUCTION_TYPE_ID)));
                auction.setDaysDurations(resultSet.getInt(DAYS_DURATION));
                auction.setStartDate(resultSet.getTimestamp(START_DATE).toLocalDateTime());
                auction.setEndDate(resultSet.getTimestamp(END_DATE).toLocalDateTime());
                auction.setCurrentMaximalBid(bidDAO.findMaxBidByAuctionId(auctionId));

                return auction;
            }
            return null;
        }
    }

    @Override
    public List<Auction> buildListFromResultSet(ResultSet resultSet) throws SQLException {
        try (LotDAO lotDAO = new LotDAO();
             BidDAO bidDAO = new BidDAO()) {

            List<Auction> auctions = new ArrayList<>();
            while (resultSet.next()) {
                Auction auction = new Auction();

                //TODO: из базы данных или resultSet???
                String auctionId = resultSet.getString(ID);
                auction.setId(Long.parseLong(auctionId));
                auction.setLot(lotDAO.findByAuctionId(auctionId));
                auction.setState(AuctionState.getStateFromValue(resultSet.getInt(AUCTION_STATE_ID)));
                auction.setType(AuctionType.getTypeFromValue(resultSet.getInt(AUCTION_TYPE_ID)));
                auction.setDaysDurations(resultSet.getInt(DAYS_DURATION));
                auction.setStartDate(resultSet.getTimestamp(START_DATE).toLocalDateTime());
                auction.setEndDate(resultSet.getTimestamp(END_DATE).toLocalDateTime());
                auction.setCurrentMaximalBid(bidDAO.findMaxBidByAuctionId(auctionId));

                auctions.add(auction);
            }
            return auctions;
        }
    }
}
