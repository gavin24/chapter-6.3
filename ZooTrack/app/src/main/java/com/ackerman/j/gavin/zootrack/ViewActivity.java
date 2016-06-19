package com.ackerman.j.gavin.zootrack;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ackerman.j.gavin.zootrack.Config.DbConstants.GlobalContext;
import com.ackerman.j.gavin.zootrack.Domain.Animal;
import com.ackerman.j.gavin.zootrack.services.AnimalService;
import com.ackerman.j.gavin.zootrack.services.Impl.AnimalServiceImpl;

import java.util.ArrayList;
import java.util.Set;

public class ViewActivity extends Activity {
    private AnimalServiceImpl activateService;
    private boolean isBound = false;
    private AnimalService activateAccountService;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        GlobalContext.context = this;
        activateService = AnimalServiceImpl.getInstance();
       // GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
       // ArrayList l = new ArrayList();
       // l = activateAccountService.getAllAnimals();
       // l = activateService.getAllAnimals();

        ArrayList<Animal> animals = new ArrayList<Animal>();
        animals =   activateService.getAllAnimals();
        ArrayList<String> names = new ArrayList<String>();

        for (Animal animal : animals)
        {
            names.add( animal.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_view,R.id.textview,names);
        ListView list = (ListView)findViewById(R.id.listView);
        list.setAdapter(adapter);
    }


    public void homeClick(View v) {

        Intent i = new Intent(this,MainActivity.class);

        startActivity(i);
    }
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
}
