package com.example.christianescobar.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CambiarIP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_ip);
        setTitle("Cambiar IP");

    }

    public void CambiarIP(View v){



        startActivity(new Intent(CambiarIP.this, MainActivity.class));
    }
}
