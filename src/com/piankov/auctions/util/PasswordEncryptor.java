package com.piankov.auctions.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {
    private static Logger LOGGER = LogManager.getLogger(PasswordEncryptor.class);

    public String encrypt(String password) throws NoSuchAlgorithmException {
        LOGGER.info("Encrypting user passwod with SHA-256 algorithm.");

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes());
        return new String(messageDigest.digest());
    }
}
