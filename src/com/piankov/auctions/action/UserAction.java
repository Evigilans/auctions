package com.piankov.auctions.action;

import com.piankov.auctions.creator.UserCreator;
import com.piankov.auctions.dao.UserDAO;
import com.piankov.auctions.entity.User;

import java.sql.SQLException;
import java.util.Map;

public class UserAction {
    public User loginUser(String login, String password) {
        User user = null;

        try (UserDAO userDAO = new UserDAO()) {
            user = userDAO.findClient(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User registerUser(Map<String, String[]> parameterMap) {
        User user = null;

        try (UserDAO userDAO = new UserDAO()) {
            UserCreator userCreator = new UserCreator();
            user = userCreator.buildEntityFromMap(parameterMap);

            long generatedId = userDAO.create(user);
            user.setId(generatedId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User findUser(String userId) {
        User user = null;

        try (UserDAO clientDAO = new UserDAO()) {
            user = clientDAO.findById(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}