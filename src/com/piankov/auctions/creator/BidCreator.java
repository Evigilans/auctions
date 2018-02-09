package com.piankov.auctions.creator;

import com.piankov.auctions.entity.Bid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BidCreator extends AbstractCreator<Bid> {
    private static final Logger LOGGER = LogManager.getLogger(BidCreator.class);

    private static final String ID = "ID";
    private static final String CLIENT_ID = "CLIENT_ID";
    private static final String AUCTION_ID = "AUCTION_ID";
    private static final String VALUE = "VALUE";

    @Override
    public Bid buildEntityFromMap(Map<String, String[]> parameterMap) {
        return null;
    }

    @Override
    public Bid buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Bid bid = new Bid();

            bid.setId(resultSet.getLong(ID));
            bid.setClientId(resultSet.getInt(CLIENT_ID));
            bid.setAuctionId(resultSet.getInt(AUCTION_ID));
            bid.setValue(resultSet.getInt(VALUE));

            return bid;
        }
        return null;
    }

    @Override
    public List<Bid> buildListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Bid> bids = new ArrayList<>();
        while (resultSet.next()) {
            Bid bid = new Bid();

            bid.setId(resultSet.getLong(ID));
            bid.setClientId(resultSet.getInt(CLIENT_ID));
            bid.setAuctionId(resultSet.getInt(AUCTION_ID));
            bid.setValue(resultSet.getInt(VALUE));

            bids.add(bid);
        }
        return bids;
    }
}
