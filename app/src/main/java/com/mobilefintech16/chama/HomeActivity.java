package com.mobilefintech16.chama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {
    String namechama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent=getIntent();
        namechama =intent.getStringExtra("create_chama");
        getSupportActionBar().setTitle(namechama);


        FloatingActionButton floatingActionButton = findViewById(R.id.fab_create);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this,CreateChama.class);
                startActivity(intent);
            }
        });
    }
}