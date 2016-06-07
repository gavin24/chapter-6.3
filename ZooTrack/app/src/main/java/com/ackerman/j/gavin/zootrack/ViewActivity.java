package com.ackerman.j.gavin.zootrack;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ackerman.j.gavin.zootrack.services.AnimalService;
import com.ackerman.j.gavin.zootrack.services.Impl.AnimalServiceImpl;

import java.util.ArrayList;

public class ViewActivity extends Activity {
    private AnimalServiceImpl activateService;
    private boolean isBound = false;
    private AnimalService activateAccountService;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ArrayList l = new ArrayList();
        l = activateAccountService.getAllAnimals();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_view,l);
        ListView list = (ListView)findViewById(R.id.listView);
        list.setAdapter(adapter);
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
