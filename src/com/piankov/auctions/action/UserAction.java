package com.piankov.auctions.action;

import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.creator.UserCreator;
import com.piankov.auctions.dao.UserDAO;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.DAOException;
import com.piankov.auctions.util.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class UserAction {
    private static Logger LOGGER = LogManager.getLogger(UserAction.class);

    public User findUser(String userId) throws ActionPerformingException {
        LOGGER.info("Performing 'Find User' action.");

        try (UserDAO clientDAO = new UserDAO()) {
            LOGGER.info("Searching user in database.");

            return clientDAO.findById(userId);
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Find User' action.", e);
        }
    }

    public User registerUser(Map<String, String[]> parameterMap) throws ActionPerformingException {
        LOGGER.info("Performing 'Register User' action.");
        User user;

        try (UserDAO userDAO = new UserDAO()) {
            LOGGER.info("Creating and inserting user in database.");
            UserCreator userCreator = new UserCreator();
            user = userCreator.buildEntityFromMap(parameterMap);

            long generatedId = userDAO.create(user);
            user.setId(generatedId);
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during perfing 'Register User' action.", e);
        }

        LOGGER.info("Registered user: " + user);
        return user;
    }


    public User updateUser(String userId, Map<String, String[]> parameterMap) throws ActionPerformingException {
        LOGGER.info("Performing 'Update User' action.");

        try (UserDAO userDAO = new UserDAO()) {
            LOGGER.info("Resetting fields and updating user in database.");
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
            throw new ActionPerformingException("An exception occurred during performing 'Update User' action.", e);
        }
    }

    public User loginUser(String email, String password) throws ActionPerformingException {
        LOGGER.info("Performing 'Login User' action.");

        try (UserDAO userDAO = new UserDAO()) {
            LOGGER.info("Finding and generation user from database.");
            PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
            String passwordHash = passwordEncryptor.encrypt(password);

            return userDAO.findClient(email, passwordHash);
        } catch (NoSuchAlgorithmException | DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Login User' action.", e);
        }
    }

    public boolean canRegister(String email) throws ActionPerformingException {
        LOGGER.info("Performing 'Can Register' action.");

        try (UserDAO userDAO = new UserDAO()) {
            LOGGER.info("Finding exists unique field in database fields.");
            return userDAO.canCreate(email);
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Can register' action.", e);
        }
    }
}
