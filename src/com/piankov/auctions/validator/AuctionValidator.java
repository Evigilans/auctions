package com.piankov.auctions.validator;

import com.piankov.auctions.constant.ParameterConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.regex.Pattern;

public class AuctionValidator {
    private static Logger LOGGER = LogManager.getLogger(AuctionValidator.class);

    private static final String POSITIVE_INTEGER_PATTERN = "^[1-9]\\d*$";

    public boolean validateAuctionData(Map<String, String[]> parameterMap) {
        LOGGER.info("Validating auction data.");

        String startPrice = parameterMap.get(ParameterConstant.PARAMETER_START_PRICE)[0];
        String days = parameterMap.get(ParameterConstant.PARAMETER_DAYS)[0];

        return isPositiveInteger(startPrice) && isPositiveInteger(days);
    }

    public boolean validateBid(Map<String, String[]> parameterMap) {
        LOGGER.info("Validating bid data.");

        String bidValue = parameterMap.get(ParameterConstant.PARAMETER_BID_VALUE)[0];
        return isPositiveInteger(bidValue);
    }

    private boolean isPositiveInteger(String value) {
        LOGGER.info("Checking if value is positive Integer.");

        return Pattern.compile(POSITIVE_INTEGER_PATTERN).matcher(value).matches();
    }
}
