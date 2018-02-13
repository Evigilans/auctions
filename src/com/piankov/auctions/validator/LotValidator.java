package com.piankov.auctions.validator;

import com.piankov.auctions.constant.ParameterConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.regex.Pattern;

public class LotValidator {
    private static Logger LOGGER = LogManager.getLogger(LotValidator.class);

    private static final String NAME_PATTERN = "^[\\p{L} .'-]+$";

    public boolean validateLotData(Map<String, String[]> parameterMap) {
        LOGGER.info("Validating lot data.");

        String name = parameterMap.get(ParameterConstant.PARAMETER_LOT_NAME)[0];
        String description = parameterMap.get(ParameterConstant.PARAMETER_LOT_DESCRIPTION)[0];

        return validateName(name) && validateDescription(description);
    }

    private boolean validateName(String name) {
        LOGGER.info("Validating lot name.");

        return name != null && Pattern.compile(NAME_PATTERN).matcher(name).matches();
    }

    private boolean validateDescription(String description) {
        LOGGER.info("Validating lot description.");

        return description.length() <= 500;
    }
}
