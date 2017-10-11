package com.example.christianescobar.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ListadoVehiculosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_vehiculos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<Vehiculo> category = new ArrayList<Vehiculo>();

        ListView lv = (ListView) findViewById(R.id.listView);

        AdapterItem adapter = new AdapterItem(this, category);

        lv.setAdapter(adapter);
    }

}