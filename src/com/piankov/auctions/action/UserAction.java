package com.piankov.auctions.action;

import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.creator.UserCreator;
import com.piankov.auctions.dao.UserDAO;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.DAOException;
import com.piankov.auctions.util.PasswordEncryptor;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class UserAction {
    public User findUser(String userId) {
        User user = null;

        try (UserDAO clientDAO = new UserDAO()) {
            user = clientDAO.findById(userId);
        } catch (DAOException e) {
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
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return user;
    }


    public User updateUser(String userId, Map<String, String[]> parameterMap) {
        try (UserDAO userDAO = new UserDAO()) {
            User user = userDAO.findById(userId);

            String email = parameterMap.get(ParameterConstant.PARAMETER_EMAIL)[0];
            if (email != null) {
                user.setEmail(email);
            }

            String password = parameterMap.get(ParameterConstant.PARAMETER_PASSWORD)[0];
            if (password != null) {
                PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
                String passwordHash = passwordEncryptor.encrypt(password);
                user.setPasswordHash(passwordHash);
            }

            String name = parameterMap.get(ParameterConstant.PARAMETER_NAME)[0];
            if (name != null) {
                user.setName(name);
            }

            return userDAO.update(user);
        } catch (NoSuchAlgorithmException | DAOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User loginUser(String email, String password) {
        User user = null;

        try (UserDAO userDAO = new UserDAO()) {
            PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
            String passwordHash = passwordEncryptor.encrypt(password);

            user = userDAO.findClient(email, passwordHash);
        } catch (NoSuchAlgorithmException | DAOException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean canRegister(String email) {
        try (UserDAO userDAO = new UserDAO()) {
            return userDAO.canCreate(email);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
