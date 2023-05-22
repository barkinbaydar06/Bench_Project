package com.example.b;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
    public static String Encrypt(String input) throws NoSuchAlgorithmException {

    MessageDigest m = MessageDigest.getInstance("MD5");

    m.update(input.getBytes());

    byte[] bytes = m.digest();

    StringBuilder s = new StringBuilder();
    for (byte aByte : bytes) {
        s.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
    }

    return s.toString();
    }

    public static String Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(input.getBytes());
        return new String(messageDigest.digest());
    }
}
