package com.piankov.auctions.creator;

import com.piankov.auctions.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserCreator extends AbstractCreator<User> {
    private static final String ID = "ID";
    private static final String LOGIN = "LOGIN";
    private static final String NAME = "NAME";
    private static final String EMAIL = "EMAIL";
    private static final String BALANCE = "BALANCE";
    private static final String CATEGORY = "CATEGORY";

    @Override
    public User buildEntityFromMap(Map<String, String> parameters) {
        return null;
    }

    @Override
    public User buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(ID));
            user.setLogin(resultSet.getString(LOGIN));
            user.setName(resultSet.getString(NAME));
            user.setEmail(resultSet.getString(EMAIL));
            user.setBalance(resultSet.getInt(BALANCE));
            //user.setCategory(rs.getInt("CATEGORY"));
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
            user.setName(resultSet.getString(NAME));
            user.setEmail(resultSet.getString(EMAIL));
            user.setBalance(resultSet.getInt(BALANCE));
            //user.setCategory(rs.getInt("CATEGORY"));
            users.add(user);
        }
        return users;
    }
}
