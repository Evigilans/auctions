package com.piankov.auctions.creator;

import com.piankov.auctions.entity.Bid;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BidCreator extends AbstractCreator<Bid> {
    private static final String ID_ROW = "ID";
    private static final String CLIENT_ID_ROW = "CLIENT_ID";
    private static final String AUCTION_ID_ROW = "AUCTION_ID";
    private static final String VALUE_ROW = "VALUE";
    private static final String MAX_VALUE = "MAX(VALUE)";

    @Override
    public Bid buildEntityFromMap(Map<String, String> parameters) {
        return null;
    }

    @Override
    public Bid buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Bid bid = new Bid();
            bid.setId(resultSet.getLong(ID_ROW));
            bid.setClientId(resultSet.getInt(CLIENT_ID_ROW));
            bid.setAuctionId(resultSet.getInt(AUCTION_ID_ROW));
            bid.setValue(resultSet.getInt(VALUE_ROW));
            return bid;
        }
        return null;
    }

    @Override
    public List<Bid> buildListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Bid> bids = new ArrayList<>();

        while (resultSet.next()) {
            Bid bid = new Bid();
            bid.setId(resultSet.getLong(ID_ROW));
            bid.setClientId(resultSet.getInt(CLIENT_ID_ROW));
            bid.setAuctionId(resultSet.getInt(AUCTION_ID_ROW));
            bid.setValue(resultSet.getInt(VALUE_ROW));
            bids.add(bid);
        }
        return bids;
    }
}
