package com.sportech.bench;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class User {

    public String UserName;
    public String SecurePassword;

    public void SetUsername(String username){
        UserName = username;
    }
    public void SetPassword(String password) throws NoSuchAlgorithmException {
        String EncryptedPassword = Password.Encrypt(password);
        SecurePassword = Password.Hash(EncryptedPassword);
    }

    public User(String username, String password) throws NoSuchAlgorithmException {
        SetUsername(username);
        SetPassword(password);
    }

    public String GetUserName(){
        return UserName;
    }
    public boolean IsThePassword(String input) throws NoSuchAlgorithmException {
        String EncryptedPassword = Password.Encrypt(input);
        return Objects.equals(SecurePassword, Password.Hash(EncryptedPassword));
    }
}
