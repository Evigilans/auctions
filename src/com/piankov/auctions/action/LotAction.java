package com.piankov.auctions.action;

import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.creator.LotCreator;
import com.piankov.auctions.dao.LotDAO;
import com.piankov.auctions.entity.Lot;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.DAOException;

import java.util.Map;

public class LotAction {
    public Lot findLotById(String lotId) {
        Lot lot = null;

        try (LotDAO lotDAO = new LotDAO()) {
            lot = lotDAO.findById(lotId);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return lot;
    }

    public Lot createLot(Map<String, String[]> parameterMap, User user) {
        Lot lot = null;

        try (LotDAO lotDAO = new LotDAO()) {
            LotCreator lotCreator = new LotCreator();
            lot = lotCreator.buildEntityFromMap(parameterMap, user);

            long lotId = lotDAO.create(lot);
            lot.setId(lotId);

            return lot;
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return lot;
    }

    public Lot updateLot(Lot lot, Map<String, String[]> parameterMap) {
        try (LotDAO lotDAO = new LotDAO()) {
            String name = parameterMap.get(ParameterConstant.PARAMETER_LOT_NAME)[0];
            String description = parameterMap.get(ParameterConstant.PARAMETER_LOT_DESCRIPTION)[0];

            lot.setName(name);
            if (description != null) {
                lot.setDescription(description);
            }

            return lotDAO.update(lot);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return lot;
    }
}
