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

    private boolean validateEmail(String email) {
        LOGGER.info("Validating email.");

        return email != null && Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    private boolean validatePassword(String password) {
        LOGGER.info("Validating password.");

        return password != null && Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }

    private boolean validateName(String name) {
        LOGGER.info("Validating user name.");

        return name != null && Pattern.compile(FULL_NAME_PATTERN).matcher(name).matches();
    }
}
