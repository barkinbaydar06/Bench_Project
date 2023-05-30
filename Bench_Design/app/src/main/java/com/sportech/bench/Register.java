package com.sportech.bench;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.NoSuchAlgorithmException;

public class Register extends AppCompatActivity {

    protected Button signUpButton, cancelButton;
    protected EditText usernameInput, passwordInput, confirmPasswordInput;
    protected AlertDialog.Builder signUpAlertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUpAlertBuilder = new AlertDialog.Builder(this);

        signUpButton = findViewById(R.id.signUpButton);
        cancelButton = findViewById(R.id.cancelRegisterButton);
        usernameInput = findViewById(R.id.usernameInputL);
        passwordInput = findViewById(R.id.signUpPasswordI);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelSignUp();
            }
        });


    }

    //Sign Up page button methods
    protected void cancelSignUp()
    {
        Intent cancelIntent = new Intent(this, MainActivity.class);
        finish();
        startActivity(cancelIntent);
    }

    protected void confirmSignUp() throws NoSuchAlgorithmException {
            if (usernameInput.getText().toString().length() != 0)
            {
                if (passwordInput.getText().toString().length() != 0)
                {
                    if (arePasswordsMatch() && !Database.UserExists(usernameInput.getText().toString()))
                    {
                        createAccount();
                    }
                    else
                    {
                        signUpAlertBuilder.setTitle("Alert");
                        signUpAlertBuilder.setMessage("Passwords do not match!");
                        signUpAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        signUpAlertBuilder.show();
                    }
                }
                else
                {
                    signUpAlertBuilder.setTitle("Alert");
                    signUpAlertBuilder.setMessage("Password cannot be empty!");
                    signUpAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    signUpAlertBuilder.show();
                }
            }
            else
            {
                signUpAlertBuilder.setTitle("Alert");
                signUpAlertBuilder.setMessage("Username cannot be empty!");
                signUpAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                signUpAlertBuilder.show();
            }
        }

    protected boolean arePasswordsMatch()
    {
        return passwordInput.getText().toString().equals(confirmPasswordInput.getText().toString());
    }

    private void createAccount() throws NoSuchAlgorithmException {
        User newUser = new User(usernameInput.getText().toString(), passwordInput.getText().toString());

        Database.AddUser(newUser);
    }

}