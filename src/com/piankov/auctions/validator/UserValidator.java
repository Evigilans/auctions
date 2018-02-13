package com.piankov.auctions.validator;

import com.piankov.auctions.constant.ParameterConstant;

import java.util.Map;
import java.util.regex.Pattern;

public class UserValidator {
    private static final String EMAIL_PATTERN = "^[\\w-+]+(\\.\\w+)*@[\\w-]+(\\.\\w+)*(\\.[a-z]{2,})$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$";
    private static final String FULL_NAME_PATTERN = "^[\\p{L} .'-]+$";

    public boolean validateRegisterData(Map<String, String[]> parameterMap) {
        String email = parameterMap.get(ParameterConstant.PARAMETER_EMAIL)[0];
        String name = parameterMap.get(ParameterConstant.PARAMETER_NAME)[0];
        String password = parameterMap.get(ParameterConstant.PARAMETER_PASSWORD)[0];

        System.out.println(validateEmail(email) + " " +  validatePassword(password) + " " + validateName(name));
        return validateEmail(email) && validatePassword(password) && validateName(name);
    }

    public boolean validateLoginData(String email, String password) {
        return validateEmail(email) && validatePassword(password);
    }

    private boolean validateEmail(String email) {
        return email != null && Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    private boolean validatePassword(String password) {
        return password != null && Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }

    private boolean validateName(String name) {
        return name != null && Pattern.compile(FULL_NAME_PATTERN).matcher(name).matches();
    }
}
