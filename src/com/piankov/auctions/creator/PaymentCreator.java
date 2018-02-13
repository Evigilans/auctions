package com.piankov.auctions.creator;

import com.piankov.auctions.entity.Payment;
import com.piankov.auctions.exception.DAOException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class PaymentCreator extends AbstractCreator<Payment> {
    @Override
    public Payment buildEntityFromMap(Map<String, String[]> parameterMap, Object... objects) {
        return null;
    }

    @Override
    public Payment buildEntityFromResultSet(ResultSet resultSet) throws DAOException {
        return null;
    }

    @Override
    public List<Payment> buildListFromResultSet(ResultSet resultSet) throws DAOException {
        return null;
    }
}
