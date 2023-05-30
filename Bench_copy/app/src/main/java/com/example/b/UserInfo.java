package com.example.b;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Objects;

public class UserInfo {

    private String UserName;
    private String SecurePassword;
    private Calendar BirthDate;

    public void SetUsername(String username){
        UserName = username;
    }
    public void SetPassword(String password) throws NoSuchAlgorithmException {
        String EncryptedPassword = Password.Encrypt(password);
        SecurePassword = Password.Hash(EncryptedPassword);
    }
    public void SetBirthDate(Calendar date){
        BirthDate = date;
    }

    public UserInfo(String username, String password) throws NoSuchAlgorithmException {
        SetUsername(username);
        SetPassword(password);
    }
    public UserInfo(String username, String password, Calendar birthDate) throws NoSuchAlgorithmException {
        SetUsername(username);
        SetPassword(password);
        SetBirthDate(birthDate);

    }

    public String GetUserName(){
        return UserName;
    }
    public Calendar GetBirthDate() {return BirthDate; }
    public boolean IsThePassword(String input) throws NoSuchAlgorithmException {
        String EncryptedPassword = Password.Encrypt(input);
        return Objects.equals(SecurePassword, Password.Hash(EncryptedPassword));
    }
}
