package com.example.christianescobar.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CambiarIP extends AppCompatActivity {

    EditText IP;
    EditText Puerto;
    TextView Error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_ip);
        setTitle("Cambiar IP");
        IP=(EditText) findViewById(R.id.txt_IP);
        Puerto=(EditText) findViewById(R.id.txt_Puerto);
        Error =(TextView) findViewById(R.id.txtError);
    }

    public void cambiarIP(View v){

        String ip=IP.getText().toString().trim();
        String puerto = Puerto.getText().toString().trim();
        V.SERVER = ip;
        V.PUERTO= puerto;
        startActivity(new Intent(CambiarIP.this, MainActivity.class));
    }
}
