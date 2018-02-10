package com.piankov.auctions.validator;

import java.util.Map;
import java.util.regex.Pattern;

public class DataValidator {
    private static final String LOGIN_PATTERN = "^[a-zA-Z0-9]{4,20}$";
    private static final String FULL_NAME_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$";
    private static final String EMAIL_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$";

    public boolean validateRegistrationData(Map<String, String[]> parameterMap) {
        return true;
    }

    public boolean validateLogin(String login) {
        return Pattern.compile(LOGIN_PATTERN).matcher(login).matches();
    }

    public boolean validateFullName(String fullName) {
        return Pattern.compile(FULL_NAME_PATTERN).matcher(fullName).matches();
    }

    public boolean validatePassword(String password) {
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }

    public boolean validateEmail(String email) {
        return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    public boolean validateLoginData(String login, String password) {
        return true;
    }

    public boolean validateBidData(Map<String, String[]> parameterMap) {
        //int value = Integer.parseInt(request.getParameter("value"));
        //String auctionId = request.getParameter("auctionId");
        //User user = (User) request.getSession().getAttribute("user");
        return true;
    }

    public boolean validateAuctionData(Map<String, String[]> parameterMap) {
        //String name = request.getParameter("name");
        //String description = request.getParameter("description");
        //int startPrice = Integer.parseInt(request.getParameter("startPrice"));
        //int days = Integer.parseInt(request.getParameter("days"));
        //User user = (User) request.getSession().getAttribute("user");
        return true;
    }
}
