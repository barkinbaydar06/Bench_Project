package com.sportech.bench;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

        protected Button loginButton;
        protected EditText usernameInput, passwordInput;
        protected String correctUsername, correctPassword, username, password;
        protected AlertDialog.Builder loginErrorDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.logInButton);

        loginErrorDialog = new AlertDialog.Builder(this);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        correctUsername = "admin";
        correctPassword = "admin123";
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginTest();
            }
        });
    }

    //Login button testing method
    protected void loginTest()
    {
        username = usernameInput.getText().toString();
        password = passwordInput.getText().toString();

        if (username.length()>0 && password.length()>0)
        {
            if (username.equals(correctUsername))
            {
                if (password.equals(correctPassword))
                {
                   Intent loginIntent = new Intent(MainActivity.this, MatchesActivity.class);
                   finish();
                   startActivity(loginIntent);
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
            }
            else
            {
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
}