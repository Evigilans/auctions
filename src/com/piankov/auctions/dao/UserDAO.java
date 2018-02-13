package com.piankov.auctions.dao;

import com.piankov.auctions.creator.UserCreator;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.DAOException;
import com.piankov.auctions.exception.EntityCreationException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    private static final String FIND_ALL_USERS = "SELECT * FROM USER";
    private static final String FIND_USER_BY_ID = "SELECT * FROM USER WHERE ID = ?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM USER WHERE ID = ?";
    private static final String UPDATE_USER = "UPDATE USER SET EMAIL = ?, PASSWORD_HASH = ?, NAME = ?, BALANCE = ?, CATEGORY = ? WHERE ID = ?";
    private static final String CREATE_USER = "INSERT INTO USER (EMAIL, PASSWORD_HASH, NAME, BALANCE, CATEGORY) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_AUTHORIZATION_DATA = "SELECT * FROM USER WHERE EMAIL = ? AND PASSWORD_HASH = ?";
    private static final String CHECK_EMAIL_EXISTANCE = "SELECT * FROM USER WHERE EMAIL = ?";

    public UserDAO() {
        super();
    }

    @Override
    public List<User> findAll() throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(FIND_ALL_USERS);
            ResultSet rs = statement.executeQuery();

            UserCreator userCreator = new UserCreator();
            return userCreator.createListFromResultSet(rs);
        } catch (SQLException | EntityCreationException e) {
            throw new DAOException("And exception occurred during finding all users.", e);
        }
    }

    @Override
    public User findById(String id) throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(FIND_USER_BY_ID);

            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();

            UserCreator userCreator = new UserCreator();
            return userCreator.createEntityFromResultSet(rs);
        } catch (SQLException | EntityCreationException e) {
            throw new DAOException("And exception occurred during finding lot by ID.", e);
        }
    }

    @Override
    public boolean delete(String userId) throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(DELETE_USER_BY_ID, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, userId);

            return statement.execute();
        } catch (SQLException e) {
            throw new DAOException("And exception occurred during deleting user.", e);
        }
    }

    @Override
    public boolean delete(User user) throws DAOException {
        return delete(String.valueOf(user.getId()));
    }

    @Override
    public long create(User user) throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getName());
            statement.setInt(4, user.getBalance());
            statement.setInt(5, user.getCategory().getValue());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DAOException("And exception occurred during creating user.", e);
        }
    }

    @Override
    public User update(User user) throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(UPDATE_USER, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getName());
            statement.setInt(4, user.getBalance());
            statement.setInt(5, user.getCategory().getValue());
            statement.setLong(6, user.getId());
            statement.executeUpdate();

            String userId = String.valueOf(user.getId());
            return findById(userId);
        } catch (SQLException e) {
            throw new DAOException("And exception occurred during updating user.", e);
        }
    }

    public User findClient(String email, String passwordHash) throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(FIND_AUTHORIZATION_DATA);

            statement.setString(1, email);
            statement.setString(2, passwordHash);
            ResultSet rs = statement.executeQuery();

            UserCreator userCreator = new UserCreator();
            return userCreator.createEntityFromResultSet(rs);
        } catch (SQLException | EntityCreationException e) {
            throw new DAOException("And exception occurred during finding user by authorization data.", e);
        }
    }

    public boolean canCreate(String email) throws DAOException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(CHECK_EMAIL_EXISTANCE);

            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();


            return !rs.next();
        } catch (SQLException e) {
            throw new DAOException("And exception occurred during checking if user can be created.", e);
        }
    }
}
