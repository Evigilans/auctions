package com.piankov.auctions.messenger;

public class ErrorMessenger {
    public static String getMessage(String language, String key) {
        LanguageType type;

        if (language != null) {
            type = LanguageType.valueOf(language.toUpperCase());
        } else {
            type = LanguageType.EN_US;
        }

        System.out.println(type.name());

        return type.getBundle().getString(key);
    }
}
