package com.piankov.auctions.dao;

import com.piankov.auctions.entity.Payment;
import com.piankov.auctions.exception.DAOException;

import java.sql.SQLException;
import java.util.List;

public class PaymentDAO extends AbstractDAO<Payment> {
    @Override
    public List<Payment> findAll() throws DAOException {
        return null;
    }

    @Override
    public Payment findById(String id) throws DAOException {
        return null;
    }

    @Override
    public boolean delete(String id) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Payment entity) throws DAOException {
        return false;
    }

    @Override
    public long create(Payment entity) throws DAOException {
        return 0;
    }

    @Override
    public Payment update(Payment entity) throws DAOException {
        return null;
    }
}
