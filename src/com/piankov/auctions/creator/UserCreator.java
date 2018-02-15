package com.piankov.auctions.creator;

import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.entity.UserCategory;
import com.piankov.auctions.exception.EntityCreationException;
import com.piankov.auctions.util.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserCreator extends AbstractCreator<User> {
    private static final Logger LOGGER = LogManager.getLogger(UserCreator.class);

    private static final String ID = "ID";
    private static final String EMAIL = "EMAIL";
    private static final String PASSWORD_HASH = "PASSWORD_HASH";
    private static final String NAME = "NAME";
    private static final String BALANCE = "BALANCE";
    private static final String CATEGORY = "CATEGORY_ID";

    @Override
    public User createEntityFromMap(Map<String, String[]> parameterMap, Object... objects) {
        LOGGER.info("Creating user from parameter map.");

        try {
            User user = new User();

            String password = parameterMap.get(ParameterConstant.PARAMETER_PASSWORD)[0];
            PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
            String passwordHash = passwordEncryptor.encrypt(password);

            LOGGER.info("Setting user fields.");
            user.setEmail(parameterMap.get(ParameterConstant.PARAMETER_EMAIL)[0]);
            user.setPasswordHash(passwordHash);
            user.setName(parameterMap.get(ParameterConstant.PARAMETER_NAME)[0]);
            user.setBalance(0);
            user.setCategory(UserCategory.CLIENT);

            LOGGER.info("Created user: " + user);
            return user;
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Cannot create user due wrong encrypting algorithm.");
            return null;
        }
    }

    @Override
    public User createEntityFromResultSet(ResultSet resultSet) throws EntityCreationException {
        LOGGER.info("Creating user from result set.");

        try {
            if (resultSet.next()) {
                User user = new User();

                LOGGER.info("Setting user fields.");
                user.setId(resultSet.getInt(ID));
                user.setEmail(resultSet.getString(EMAIL));
                user.setPasswordHash(resultSet.getString(PASSWORD_HASH));
                user.setName(resultSet.getString(NAME));
                user.setBalance(resultSet.getInt(BALANCE));
                user.setCategory(UserCategory.getCategoryFromValue(resultSet.getInt(CATEGORY)));

                LOGGER.info("Created user: " + user);
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new EntityCreationException("Cannot create user from received result set.", e);
        }
    }

    @Override
    public List<User> createListFromResultSet(ResultSet resultSet) throws EntityCreationException {
        LOGGER.info("Creating list of user from result set.");

        try {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();

                LOGGER.info("Setting user fields.");
                user.setId(resultSet.getInt(ID));
                user.setEmail(resultSet.getString(EMAIL));
                user.setPasswordHash(resultSet.getString(PASSWORD_HASH));
                user.setName(resultSet.getString(NAME));
                user.setBalance(resultSet.getInt(BALANCE));
                user.setCategory(UserCategory.getCategoryFromValue(resultSet.getInt(CATEGORY)));

                LOGGER.info("Added user to list: " + user);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new EntityCreationException("Cannot create users list from received result set.", e);
        }
    }
}
