package com.piankov.auctions.creator;

import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.dao.UserDAO;
import com.piankov.auctions.entity.Lot;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.DAOException;
import com.piankov.auctions.exception.EntityCreationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LotCreator extends AbstractCreator<Lot> {
    private static final Logger LOGGER = LogManager.getLogger(LotCreator.class);

    private static final String ID = "ID";
    private static final String OWNER_ID = "OWNER_ID";
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";


    @Override
    public Lot createEntityFromMap(Map<String, String[]> parameterMap, Object... objects) {
        LOGGER.info("Creating lot from parameter map.");

        Lot lot = new Lot();

        User user = (User) objects[0];

        LOGGER.info("Setting lot fields.");
        lot.setOwner(user);
        lot.setName(parameterMap.get(ParameterConstant.PARAMETER_LOT_NAME)[0]);
        lot.setDescription(parameterMap.get(ParameterConstant.PARAMETER_LOT_DESCRIPTION)[0]);

        LOGGER.info("Created lot: " + lot);
        return lot;
    }

    @Override
    public Lot createEntityFromResultSet(ResultSet resultSet) throws EntityCreationException {
        LOGGER.info("Creating lot from result set.");

        try (UserDAO userDAO = new UserDAO()) {

            if (resultSet.next()) {
                Lot lot = new Lot();

                LOGGER.info("Setting lot fields.");
                lot.setId(resultSet.getInt(ID));
                lot.setOwner(userDAO.findById(resultSet.getString(OWNER_ID)));
                lot.setName(resultSet.getString(NAME));
                lot.setDescription(resultSet.getString(DESCRIPTION));

                LOGGER.info("Created lot: " + lot);
                return lot;
            }
            return null;
        } catch (SQLException | DAOException e) {
            throw new EntityCreationException("Cannot create lot from received result set.", e);
        }
    }

    @Override
    public List<Lot> createListFromResultSet(ResultSet resultSet) throws EntityCreationException {
        LOGGER.info("Creating list of lots from result set.");

        try (UserDAO userDAO = new UserDAO()) {

            List<Lot> lots = new ArrayList<>();
            while (resultSet.next()) {
                Lot lot = new Lot();

                LOGGER.info("Setting lot fields.");
                lot.setId(resultSet.getInt(ID));
                lot.setOwner(userDAO.findById(resultSet.getString(OWNER_ID)));
                lot.setName(resultSet.getString(NAME));
                lot.setDescription(resultSet.getString(DESCRIPTION));

                LOGGER.info("Added lot to list: " + lot);
                lots.add(lot);
            }
            return lots;
        } catch (SQLException | DAOException e) {
            throw new EntityCreationException("Cannot create lots list from received result set.", e);
        }
    }
}
