package com.mobilefintech16.chama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateChama extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
 private Button button;
 EditText create_chama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chama);

        //Payment frequency spinner
        Spinner spinner= (Spinner) findViewById(R.id.frequency_spinner);
        //Adapter Array
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this, R.array.frequency_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        //  Text entry passing the data input for Chama creation

        create_chama =findViewById(R.id.create_chama);

        //Create button
        Button button1=(Button) findViewById(R.id.create_button) ;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namechama=create_chama.getText().toString();
                Intent intent = new Intent(CreateChama.this,HomeActivity.class);
                intent.putExtra("create_chama",namechama);
                startActivity(intent);
            }
        });
        //Cancel button
        Button button=(Button) findViewById(R.id.cancel_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(CreateChama.this,HomeActivity.class);
                startActivity(intent);

            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}