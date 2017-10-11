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

        ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

        Vehiculo vh = new Vehiculo(11, "TOYOTA", "hilux", 2011, "Estados Unidos", 35000);
        Vehiculo vh2 = new Vehiculo(12, "TOYOTA", "hilux", 2011, "Estados Unidos", 35000);

        vehiculos.add(vh);
        vehiculos.add(vh2);

        ListView lv = (ListView) findViewById(R.id.listView);

        AdapterItem adapter = new AdapterItem(this, vehiculos);

        lv.setAdapter(adapter);
    }

}
