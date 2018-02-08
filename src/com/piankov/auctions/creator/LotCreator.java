package com.piankov.auctions.creator;

import com.piankov.auctions.dao.UserDAO;
import com.piankov.auctions.entity.Lot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LotCreator extends AbstractCreator<Lot> {
    private static final String ID = "ID";
    private static final String OWNER_ID = "OWNER_ID";
    private static final String START_PRICE = "START_PRICE";
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";

    @Override
    public Lot buildEntityFromMap(Map<String, String> parameters) {
        return null;
    }

    @Override
    public Lot buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        UserDAO userDAO = new UserDAO();

        if (resultSet.next()) {
            Lot lot = new Lot();
            lot.setId(resultSet.getInt(ID));
            lot.setOwner(userDAO.findById(resultSet.getString(OWNER_ID)));
            lot.setStartPrice(resultSet.getInt(START_PRICE));
            lot.setName(resultSet.getString(NAME));
            lot.setDescription(resultSet.getString(DESCRIPTION));
            return lot;
        }
        return null;
    }

    @Override
    public List<Lot> buildListFromResultSet(ResultSet resultSet) throws SQLException {
        UserDAO userDAO = new UserDAO();

        List<Lot> lots = new ArrayList<>();
        while (resultSet.next()) {
            Lot lot = new Lot();
            lot.setId(resultSet.getInt(ID));
            lot.setOwner(userDAO.findById(resultSet.getString(OWNER_ID)));
            lot.setStartPrice(resultSet.getInt(START_PRICE));
            lot.setName(resultSet.getString(NAME));
            lot.setDescription(resultSet.getString(DESCRIPTION));
            lots.add(lot);
        }
        return lots;
    }
}
