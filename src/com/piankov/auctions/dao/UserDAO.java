package com.piankov.auctions.dao;

import com.piankov.auctions.creator.UserCreator;
import com.piankov.auctions.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    private static final String FIND_ALL_USERS = "SELECT * FROM USER";
    private static final String FIND_USER_BY_ID = "SELECT * FROM USER WHERE ID = ?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM USER WHERE ID = ?";
    private static final String UPDATE_USER = "UPDATE USER SET LOGIN = ?, PASSWORD_HASH = ?, NAME = ?, EMAIL = ?, BALANCE = ?, CATEGORY = ? WHERE ID = ?";
    private static final String CREATE_USER = "INSERT INTO USER (LOGIN, PASSWORD_HASH, NAME, EMAIL, BALANCE, CATEGORY) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_AUTHORIZATION_DATA = "SELECT * FROM USER WHERE LOGIN = ? AND PASSWORD_HASH = ?";

    public UserDAO() {
        super();
    }

    @Override
    public List<User> findAll() throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(FIND_ALL_USERS);

        ResultSet rs = statement.executeQuery();
        UserCreator userCreator = new UserCreator();

        return userCreator.buildListFromResultSet(rs);
    }

    @Override
    public User findById(String id) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(FIND_USER_BY_ID);

        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();

        UserCreator userCreator = new UserCreator();
        return userCreator.buildEntityFromResultSet(rs);
    }

    @Override
    public boolean delete(String userId) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(DELETE_USER_BY_ID, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, userId);

        return statement.execute();
    }

    @Override
    public boolean delete(User user) throws SQLException {
        return delete(String.valueOf(user.getId()));
    }

    @Override
    public long create(User user) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, user.getLogin());
        statement.setString(2, String.valueOf(user.getPasswordHash()));
        statement.setString(3, user.getName());
        statement.setString(4, user.getEmail());
        statement.setInt(5, user.getBalance());
        statement.setInt(6, user.getCategory().ordinal());
        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();

        return resultSet.getLong(1);
    }

    @Override
    public User update(User user) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(UPDATE_USER, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, user.getLogin());
        statement.setString(2, String.valueOf(user.getPasswordHash()));
        statement.setString(3, user.getName());
        statement.setString(4, user.getEmail());
        statement.setInt(5, user.getBalance());
        statement.setInt(6, user.getCategory().ordinal());
        statement.executeUpdate();

        String userId = String.valueOf(user.getId());
        return findById(userId);
    }

    public User findClient(String clientName, String passwordHash) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(FIND_AUTHORIZATION_DATA);

        statement.setString(1, clientName);
        statement.setString(2, passwordHash);
        ResultSet rs = statement.executeQuery();

        UserCreator userCreator = new UserCreator();
        return userCreator.buildEntityFromResultSet(rs);
    }
}
