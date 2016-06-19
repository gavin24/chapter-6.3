package com.ackerman.j.gavin.zootrack;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ackerman.j.gavin.zootrack.Config.Util.App;
import com.ackerman.j.gavin.zootrack.Domain.Animal;
import com.ackerman.j.gavin.zootrack.services.AnimalService;
import com.ackerman.j.gavin.zootrack.services.Impl.AnimalServiceImpl;

public class SubmitActivity extends AppCompatActivity {
    private AnimalServiceImpl activateService;
    private boolean isBound = false;
    private AnimalService activateAccountService;
    TextView name,country,age,species;
    Intent t;
    private ServiceConnection connection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            AnimalServiceImpl.AnimalServiceLocalBinder binder = (AnimalServiceImpl.AnimalServiceLocalBinder) service;
            activateService = binder.getService();
            isBound = true;
        }

        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Intent intent = new Intent(this, AnimalServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

        activateService = AnimalServiceImpl.getInstance();
        name = (TextView)findViewById(R.id.name);
        species = (TextView)findViewById(R.id.species);
        age = (TextView)findViewById(R.id.age);
        country = (TextView)findViewById(R.id.country);

       String animalName = getIntent().getStringExtra("animalName");
       String animalCountry = getIntent().getStringExtra("animalCountry");
       String animalSpecies = getIntent().getStringExtra("animalSpecies");
       String animalAge = getIntent().getStringExtra("animalAge");

        name.setText(animalName);
        species.setText(animalSpecies);
        age.setText(animalAge);
        country.setText(animalCountry);

    }

    public void onClick(View v) {

        String nameText = name.getText().toString();
        int ageText = Integer.parseInt(age.getText().toString());
        String speciesText = species.getText().toString();
        String countryText = country.getText().toString();


             Animal animal = new Animal.Builder()
                .age(ageText)
                .Country(countryText)
                .name(nameText)
                .species(speciesText)
                .build();


        activateService.addAnimal(animal);

        Intent intentLogin = new Intent(this, ViewActivity.class);
        startActivity(intentLogin);
        finish();
    }


}
