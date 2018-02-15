package com.piankov.auctions.validator;

import com.piankov.auctions.constant.ParameterConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.regex.Pattern;

public class UserValidator {
    private static Logger LOGGER = LogManager.getLogger(UserValidator.class);

    private static final String EMAIL_PATTERN = "^[\\w-+]+(\\.\\w+)*@[\\w-]+(\\.\\w+)*(\\.[a-z]{2,})$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$";
    private static final String FULL_NAME_PATTERN = "^[\\p{L} .'-]+$";
    private static final String POSITIVE_INTEGER_PATTERN = "^[1-9]\\d*$";

    public boolean validateRegisterData(Map<String, String[]> parameterMap) {
        LOGGER.info("Validating register data.");

        String email = parameterMap.get(ParameterConstant.PARAMETER_EMAIL)[0];
        String name = parameterMap.get(ParameterConstant.PARAMETER_NAME)[0];
        String password = parameterMap.get(ParameterConstant.PARAMETER_PASSWORD)[0];

        return validateEmail(email) && validatePassword(password) && validateName(name);
    }

    public boolean validateLoginData(String email, String password) {
        LOGGER.info("Validating login.");

        return validateEmail(email) && validatePassword(password);
    }

    public boolean validateEmail(String email) {
        LOGGER.info("Validating email.");

        return email != null && Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    public boolean validatePassword(String password) {
        LOGGER.info("Validating password.");

        return password != null && Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }

    public boolean validateName(String name) {
        LOGGER.info("Validating user name.");

        return name != null && Pattern.compile(FULL_NAME_PATTERN).matcher(name).matches();
    }

    public boolean validateBalance(String balance) {
        LOGGER.info("Checking if balance is positive Integer.");

        return balance != null && Pattern.compile(POSITIVE_INTEGER_PATTERN).matcher(balance).matches();

    }
}
