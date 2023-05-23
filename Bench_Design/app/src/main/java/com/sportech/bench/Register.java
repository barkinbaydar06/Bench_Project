package com.sportech.bench;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    protected Button signUpButton, cancelButton;
    protected EditText emailInput, passwordInput, confirmPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUpButton = findViewById(R.id.signUpButton);
        cancelButton = findViewById(R.id.cancelRegisterButton);
        emailInput = findViewById(R.id.emailInput);
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

}