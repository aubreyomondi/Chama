package com.mobilefintech16.chama;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout username_text, password_text;
    Button callSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        callSignUp = findViewById(R.id.call_signup_btn);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });

        username_text = (TextInputLayout) findViewById(R.id.username_text);
        password_text = (TextInputLayout) findViewById(R.id.password_text);
    }

    private Boolean validateUserName(){
        String val = username_text.getEditText().getText().toString();

        if (val.isEmpty()) {
            username_text.setError("Field cannot be empty");
            return false;
        } else {
            username_text.setError(null);
            username_text.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword(){
        String val = password_text.getEditText().getText().toString();

        if (val.isEmpty()) {
            password_text.setError("Field cannot be empty");
            return false;
        } else {
            password_text.setError(null);
            password_text.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view){

        if (!validateUserName() | !validatePassword())
        {
            return;
        }

        // Get all values as strings
        String username = username_text.getEditText().getText().toString();
        String password = password_text.getEditText().getText().toString();
    }
}