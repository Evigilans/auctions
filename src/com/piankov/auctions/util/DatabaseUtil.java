package com.piankov.auctions.util;

import com.piankov.auctions.auction.Lot;
import com.piankov.auctions.client.Client;
import com.piankov.auctions.connection.ConnectionWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    public static Client findClient(ConnectionWrapper connectionWrapper, String clientName, String password) throws SQLException {
        String query = "SELECT C.LOGIN, C.PASSWORD_HASH FROM CLIENT C WHERE C.LOGIN = ? AND C.PASSWORD_HASH = ?";
        PreparedStatement statement = connectionWrapper.prepareStatement(query);
        statement.setString(1, clientName);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            Client client = new Client();
            client.setName(clientName);
            client.setPasswordHash(password);
            return client;
        }
        return null;
    }

    public static Client findClient(ConnectionWrapper connectionWrapper, String clientName) throws SQLException {
        String sql = "SELECT C.LOGIN, C.PASSWORD_HASH FROM CLIENT WHERE C.LOGIN = ?";
        PreparedStatement pstm = connectionWrapper.prepareStatement(sql);
        pstm.setString(1, clientName);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            Client client = new Client();
            client.setName(clientName);
            client.setPasswordHash(rs.getString("PASSWORD_HASH"));
            return client;
        }
        return null;
    }

    public static List<Lot> queryProduct(ConnectionWrapper connectionWrapper) throws SQLException {
        String sql = "";
        PreparedStatement pstm = connectionWrapper.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Lot> list = new ArrayList<Lot>();
        while (rs.next()) {
            Lot product = new Lot();
            list.add(product);
        }
        return list;
    }

    public static Lot findProduct(ConnectionWrapper connectionWrapper, String code) throws SQLException {
        String sql = "";
        PreparedStatement pstm = connectionWrapper.prepareStatement(sql);
        pstm.setString(1, code);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Lot product = new Lot();
            return product;
        }
        return null;
    }

    public static void updateProduct(ConnectionWrapper connectionWrapper, Lot lot) throws SQLException {
        String sql = "";
        PreparedStatement pstm = connectionWrapper.prepareStatement(sql);
        pstm.executeUpdate();
    }

    public static void insertProduct(ConnectionWrapper connectionWrapper, Lot product) throws SQLException {
        String sql = "";
        PreparedStatement pstm = connectionWrapper.prepareStatement(sql);
        pstm.executeUpdate();
    }

    public static void deleteProduct(ConnectionWrapper connectionWrapper, String code) throws SQLException {
        String sql = "";
        PreparedStatement pstm = connectionWrapper.prepareStatement(sql);
        pstm.executeUpdate();
    }
}
