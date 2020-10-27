package com.mobilefintech16.chama;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout username_text, password_text;
    Button callSignUp;
    ProgressBar progressBar;

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
        progressBar = new ProgressBar(LoginActivity.this);
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
        }else{
            isUser();
        }
    }

    private void isUser() {
        progressBar.setVisibility(View.VISIBLE);
        final String userEnteredUsername = username_text.getEditText().getText().toString().trim();
        final String userEnteredPassword = password_text.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    username_text.setError(null);
                    username_text.setErrorEnabled(false);
                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        username_text.setError(null);
                        username_text.setErrorEnabled(false);

                        String fullName = dataSnapshot.child(userEnteredUsername).child("fullName").getValue(String.class);
                        String username = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String nationalId = dataSnapshot.child(userEnteredUsername).child("nationalId").getValue(String.class);
                        String phoneNo = dataSnapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String email = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String password = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("username", username);
                        intent.putExtra("nationalId", nationalId);
                        intent.putExtra("phoneNo", phoneNo);
                        intent.putExtra("email", email);
                        intent.putExtra("password", password);
                        startActivity(intent);
                    } else {
                        progressBar.setVisibility(View.GONE);
                        password_text.setError("Wrong Password");
                        password_text.requestFocus();
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    username_text.setError("No such User exist");
                    username_text.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}