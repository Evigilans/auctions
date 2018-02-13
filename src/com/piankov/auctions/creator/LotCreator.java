package com.piankov.auctions.creator;

import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.dao.UserDAO;
import com.piankov.auctions.entity.Lot;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.DAOException;
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
    public Lot buildEntityFromMap(Map<String, String[]> parameterMap, Object... objects) {
        Lot lot = new Lot();

        User user = (User) objects[0];

        lot.setOwner(user);
        lot.setName(parameterMap.get(ParameterConstant.PARAMETER_LOT_NAME)[0]);
        lot.setDescription(parameterMap.get(ParameterConstant.PARAMETER_LOT_DESCRIPTION)[0]);

        return lot;
    }

    @Override
    public Lot buildEntityFromResultSet(ResultSet resultSet) throws DAOException {
        try (UserDAO userDAO = new UserDAO()) {

            if (resultSet.next()) {
                Lot lot = new Lot();

                lot.setId(resultSet.getInt(ID));
                lot.setOwner(userDAO.findById(resultSet.getString(OWNER_ID)));
                lot.setName(resultSet.getString(NAME));
                lot.setDescription(resultSet.getString(DESCRIPTION));

                return lot;
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }

    @Override
    public List<Lot> buildListFromResultSet(ResultSet resultSet) throws DAOException {
        try (UserDAO userDAO = new UserDAO()) {

            List<Lot> lots = new ArrayList<>();
            while (resultSet.next()) {
                Lot lot = new Lot();

                lot.setId(resultSet.getInt(ID));
                lot.setOwner(userDAO.findById(resultSet.getString(OWNER_ID)));
                lot.setName(resultSet.getString(NAME));
                lot.setDescription(resultSet.getString(DESCRIPTION));

                lots.add(lot);
            }
            return lots;
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }
}
