package com.mobilefintech16.chama;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout full_name_text, username_text, id_text, phone_text, email_text, password_text;
    Button callLogin;
    //String fullName, username, nationalId, phoneNo, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_sign_up);

        callLogin = findViewById(R.id.call_login_btn);

        callLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        full_name_text = (TextInputLayout) findViewById(R.id.full_name_text);
        username_text = (TextInputLayout) findViewById(R.id.username_text);
        id_text = (TextInputLayout) findViewById(R.id.id_text);
        phone_text = (TextInputLayout) findViewById(R.id.phone_text);
        email_text = (TextInputLayout) findViewById(R.id.email_text);
        password_text = (TextInputLayout) findViewById(R.id.password_text);
    }

    private Boolean validateFullName(){
        String val = full_name_text.getEditText().getText().toString();

        if (val.isEmpty()) {
            full_name_text.setError("Field cannot be empty");
            return false;
        }
        else {
            full_name_text.setError(null);
            full_name_text.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUserName(){
        String val = username_text.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            username_text.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            username_text.setError("Username too long");
            return false;
        } else if (val.length() < 4) {
            username_text.setError("Username too short");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            username_text.setError("White Spaces are not allowed");
            return false;
        } else {
            username_text.setError(null);
            username_text.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateNationalId(){
        String val = id_text.getEditText().getText().toString();

        if (val.isEmpty()) {
            id_text.setError("Field cannot be empty");
            return false;
        } else {
            id_text.setError(null);
            id_text.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo(){
        String val = phone_text.getEditText().getText().toString();

        if (val.isEmpty()) {
            phone_text.setError("Field cannot be empty");
            return false;
        } else {
            phone_text.setError(null);
            phone_text.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail(){
        String val = email_text.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email_text.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email_text.setError("Invalid email address");
            return false;
        } else {
            email_text.setError(null);
            email_text.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword(){
        String val = password_text.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            password_text.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            password_text.setError("Password is too weak");
            return false;
        } else {
            password_text.setError(null);
            password_text.setErrorEnabled(false);
            return true;
        }
    }

    public void registerUser(View view){

        if (!validateFullName() | !validateUserName() | !validateNationalId() | !validatePhoneNo() | !validateEmail() | !validatePassword())
        {
            return;
        }

        // Get all values as strings
        String fullName = full_name_text.getEditText().getText().toString();
        String username = username_text.getEditText().getText().toString();
        String nationalId = id_text.getEditText().getText().toString();
        String phoneNo = phone_text.getEditText().getText().toString();
        String email = email_text.getEditText().getText().toString();
        String password = password_text.getEditText().getText().toString();
    }
}