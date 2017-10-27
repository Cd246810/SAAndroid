package com.example.christianescobar.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CambiarIP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_ip);
        setTitle("Cambiar IP");

    }

    public void cambiarIP(View v){

        EditText IP=(EditText) findViewById(R.id.txt_Tarjeta);
        String ip=IP.getText().toString().trim();
        String ipA[]=ip.split(".");
        boolean error=false;
        if(ipA.length!=4){
            error=true;
        }else{
            for(String i:ipA){
                try{
                    int num=Integer.parseInt(i);
                    if(num<0 || num>255){
                        error=true;
                        break;
                    }
                }catch(Exception ex){
                    error=true;
                    break;
                }
            }
        }
        if(error){
            IP.setTextColor(Color.RED);
        }else{
            IP.setTextColor(Color.BLUE);
            V.SERVER = ip;
            startActivity(new Intent(CambiarIP.this, MainActivity.class));
        }
    }
}
