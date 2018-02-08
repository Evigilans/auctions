package com.piankov.auctions.dao;

import com.piankov.auctions.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public class PaymentDAO extends AbstractDAO<Payment> {
    @Override
    public List<Payment> findAll() throws SQLException {
        return null;
    }

    @Override
    public Payment findById(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Payment entity) throws SQLException {
        return false;
    }

    @Override
    public long create(Payment entity) throws SQLException {
        return 0;
    }

    @Override
    public Payment update(Payment entity) throws SQLException {
        return null;
    }
}
