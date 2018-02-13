package com.piankov.auctions.validator;

import com.piankov.auctions.constant.ParameterConstant;

import java.util.Map;
import java.util.regex.Pattern;

public class LotValidator {
    private static final String NAME_PATTERN = "^[a-zA-Z0-9]{1,25}$";

    public boolean validateLotData(Map<String, String[]> parameterMap) {
        String name = parameterMap.get(ParameterConstant.PARAMETER_LOT_NAME)[0];
        String description = parameterMap.get(ParameterConstant.PARAMETER_LOT_DESCRIPTION)[0];
        return validateName(name) && validateDescription(description);
    }

    private boolean validateName(String name) {
        return name != null && Pattern.compile(NAME_PATTERN).matcher(name).matches();
    }

    private boolean validateDescription(String description) {
        return true;
    }
}
