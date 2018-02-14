package com.piankov.auctions.action;

import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.creator.LotCreator;
import com.piankov.auctions.dao.LotDAO;
import com.piankov.auctions.entity.Lot;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class LotAction {
    private static Logger LOGGER = LogManager.getLogger(LotAction.class);

    public Lot findLotById(String lotId) throws ActionPerformingException {
        LOGGER.info("Performing 'Find Lot By ID' action.");

        try (LotDAO lotDAO = new LotDAO()) {
            LOGGER.info("Searching lot in database.");

            return lotDAO.findById(lotId);
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Find Lot By ID' action.", e);
        }
    }

    public Lot createLot(Map<String, String[]> parameterMap, User user) throws ActionPerformingException {
        LOGGER.info("Performing 'Create Lot' action.");

        try (LotDAO lotDAO = new LotDAO()) {
            LOGGER.info("Creating and inserting lot in database.");
            LotCreator lotCreator = new LotCreator();
            Lot lot = lotCreator.createEntityFromMap(parameterMap, user);

            long lotId = lotDAO.create(lot);
            lot.setId(lotId);

            return lot;
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Create Lot' action.", e);
        }
    }

    public Lot updateLot(Lot lot, Map<String, String[]> parameterMap) throws ActionPerformingException {
        LOGGER.info("Performing 'Update Lot' action.");

        try (LotDAO lotDAO = new LotDAO()) {
            LOGGER.info("Resetting fields and update lot in database.");
            String name = parameterMap.get(ParameterConstant.PARAMETER_LOT_NAME)[0];
            String description = parameterMap.get(ParameterConstant.PARAMETER_LOT_DESCRIPTION)[0];

            lot.setName(name);
            if (description != null) {
                lot.setDescription(description);
            }

            return lotDAO.update(lot);
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Update Lot' action.", e);
        }
    }
}