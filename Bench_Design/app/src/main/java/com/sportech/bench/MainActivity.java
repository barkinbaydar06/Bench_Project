package com.sportech.bench;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

        protected Button loginButton, signUpButton;
        protected EditText usernameInput, passwordInput;
        protected String correctUsername, correctPassword, username, password;
        protected AlertDialog.Builder loginErrorDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.logInButton);
        signUpButton = findViewById(R.id.signUpButtonL);

        loginErrorDialog = new AlertDialog.Builder(this);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    loginTest();
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    goToSignUp();
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    //Login button testing method
    protected void loginTest() throws NoSuchAlgorithmException {
        username = usernameInput.getText().toString();
        password = passwordInput.getText().toString();

        if (username.length() > 0 && password.length() > 0){
            Database.UserExists(username, new Database.BooleanCallback() {
                @Override
                public void onCallback(boolean value) {
                    if(value){
                        Database.GetUserInfo(username, new Database.UserCallback() {
                            @Override
                            public void onCallback(User value) {
                                assert value != null;

                                try {
                                    if(value.IsThePassword(password)){
                                        Intent loginIntent = new Intent(MainActivity.this, MatchesActivity.class);
                                        finish();
                                        startActivity(loginIntent);

                                        Database.currentUser = value;
                                    }
                                    else
                                    {
                                        loginErrorDialog.setTitle("Alert");
                                        loginErrorDialog.setMessage("Incorrect password!");

                                        loginErrorDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                        loginErrorDialog.show();
                                    }
                                } catch (NoSuchAlgorithmException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });

                    }
                    else {
                        loginErrorDialog.setTitle("Alert");
                        loginErrorDialog.setMessage("Username not found!");

                        loginErrorDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        loginErrorDialog.show();
                    }
                }
            });
        }
        else
        {
            loginErrorDialog.setTitle("Alert");
            loginErrorDialog.setMessage("Username or Password cannot be empty!");

            loginErrorDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            loginErrorDialog.show();
        }
    }


    protected void goToSignUp() throws NoSuchAlgorithmException {
        Intent signIntent = new Intent(this, Register.class);
        finish();
        startActivity(signIntent);
    }
}