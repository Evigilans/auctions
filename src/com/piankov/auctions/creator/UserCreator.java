package com.piankov.auctions.creator;

import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.entity.UserCategory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserCreator extends AbstractCreator<User> {
    private static final Logger LOGGER = LogManager.getLogger(UserCreator.class);

    private static final String ID = "ID";
    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD_HASH = "PASSWORD_HASH";
    private static final String NAME = "NAME";
    private static final String EMAIL = "EMAIL";
    private static final String BALANCE = "BALANCE";
    private static final String CATEGORY = "CATEGORY";


    @Override
    public User buildEntityFromMap(Map<String, String[]> parameterMap, Object... objects) {
        User user = new User();

        user.setLogin(parameterMap.get(ParameterConstant.PARAMETER_LOGIN)[0]);
        //TODO: Что делать с паролем???
        //user.setPasswordHash(parameterMap.get(ParameterConstant.PARAMETER_LOGIN)[0]);
        user.setName(parameterMap.get(ParameterConstant.PARAMETER_NAME)[0]);
        user.setEmail(parameterMap.get(ParameterConstant.PARAMETER_EMAIL)[0]);
        user.setBalance(0);
        user.setCategory(UserCategory.CLIENT);

        return user;
    }

    @Override
    public User buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            User user = new User();

            user.setId(resultSet.getInt(ID));
            user.setLogin(resultSet.getString(LOGIN));
            user.setLogin(resultSet.getString(PASSWORD_HASH));
            user.setName(resultSet.getString(NAME));
            user.setEmail(resultSet.getString(EMAIL));
            user.setBalance(resultSet.getInt(BALANCE));
            user.setCategory(UserCategory.getCategoryFromValue(resultSet.getInt(CATEGORY)));

            return user;
        }
        return null;
    }

    @Override
    public List<User> buildListFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();

            user.setId(resultSet.getInt(ID));
            user.setLogin(resultSet.getString(LOGIN));
            user.setLogin(resultSet.getString(PASSWORD_HASH));
            user.setName(resultSet.getString(NAME));
            user.setEmail(resultSet.getString(EMAIL));
            user.setBalance(resultSet.getInt(BALANCE));
            user.setCategory(UserCategory.getCategoryFromValue(resultSet.getInt(CATEGORY)));

            users.add(user);
        }
        return users;
    }
}