package com.example.christianescobar.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Inicio de Sesión");
    }

    public void IniciarSesion(View v)
    {
        setContentView(R.layout.activity_main);
        final TextView usuario=(TextView) findViewById(R.id.txt_Usuario);
        final TextView contrasenia=(TextView) findViewById(R.id.txt_Contrasenia);
        final TextView resultado=(TextView) findViewById(R.id.txt_Resultado);

        Comunicacion c = new Comunicacion();
        Json json=c.sendRequest(Comunicacion.CREAR_CUENTA,this,new String[][]
                {{"username", (String) usuario.getText()},
                        {"password",(String)contrasenia.getText()}});
        if(!json.hasError()){
            Object o;
            o=json.getField("status",V.STRING);
            if(json.hasError()||o==null){
                resultado.setText(json.getError());
            }else{
                String salida;
                int estado = (int)o;
                if(estado!=0){
                    salida =(String)json.getField("descripcion",V.STRING);
                    resultado.setTextColor(Color.RED);
                }else{
                    salida = "Usuario creado.";
                    resultado.setTextColor(Color.GREEN);
                }
                usuario.setText(salida);
            }
        }else{
            usuario.setText(json.getError());
        }
    }

}