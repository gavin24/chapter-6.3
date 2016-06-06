package com.ackerman.j.gavin.zootrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ackerman.j.gavin.zootrack.services.AnimalService;
import com.ackerman.j.gavin.zootrack.services.Impl.AnimalServiceImpl;

public class AddScreen extends AppCompatActivity {

    EditText name,country,age,species;
    Intent t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen);
        name = (EditText)findViewById(R.id.name);
        species = (EditText)findViewById(R.id.species);
        age = (EditText)findViewById(R.id.age);
        country = (EditText)findViewById(R.id.country);
        name.setText(t.getStringExtra("name"));
        species.setText(t.getStringExtra("species"));
        age.setText(t.getStringExtra("age"));
        country.setText(t.getStringExtra("country"));
    }

    protected void onClick(View v) {

        Intent i = new Intent(this,SubmitActivity.class);

        startActivity(i);
    }



}