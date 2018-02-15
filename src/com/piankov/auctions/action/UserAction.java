package com.piankov.auctions.action;

import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.creator.UserCreator;
import com.piankov.auctions.dao.UserDAO;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.entity.UserCategory;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.DAOException;
import com.piankov.auctions.util.PasswordEncryptor;
import com.piankov.auctions.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.util.List;
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
            user = userCreator.createEntityFromMap(parameterMap);

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
            UserValidator userValidator = new UserValidator();
            User user = userDAO.findById(userId);

            String name = parameterMap.get(ParameterConstant.PARAMETER_NAME)[0];
            if (name != null && userValidator.validateName(name)) {
                user.setName(name);
            }

            String balance = parameterMap.get(ParameterConstant.PARAMETER_BALANCE)[0];
            if (balance != null && userValidator.validateBalance(balance)) {
                int newBalance = Integer.parseInt(balance);
                user.setBalance(newBalance);
            }

            return userDAO.update(user);
        } catch (DAOException e) {
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
            LOGGER.info("Finding exists unique field in database.");
            return userDAO.canCreate(email);
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Can register' action.", e);
        }
    }

    public void promoteUser(User promotedUser) throws ActionPerformingException {
        LOGGER.info("Performing 'Promote User' action.");

        try (UserDAO userDAO = new UserDAO()) {
            LOGGER.info("Setting category field and updating user in database.");

            promotedUser.setCategory(UserCategory.ADVANCED);
            userDAO.update(promotedUser);
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Promote User' action.", e);
        }
    }

    public List<User> findAllUsers() throws ActionPerformingException {
        LOGGER.info("Performing 'Find All Users' action.");

        try (UserDAO userDAO = new UserDAO()) {
            LOGGER.info("Finding all users in database.");

            return userDAO.findAll();
        } catch (DAOException e) {
            throw new ActionPerformingException("An exception occurred during performing 'Find All Users' action.", e);
        }
    }
}
