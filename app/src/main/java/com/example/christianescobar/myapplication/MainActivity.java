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

    private TextView resultado;
    private TextView usuario;
    private TextView contrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Inicio de Sesión");
        resultado=(TextView) findViewById(R.id.txt_Resultado);
        usuario=(TextView) findViewById(R.id.txt_Usuario);
        contrasenia=(TextView) findViewById(R.id.txt_Contrasenia);
    }

    public void IniciarSesion(View v)
    {
        Comunicacion c = new Comunicacion();
        String txt_usuario= usuario.getText().toString();
        String txt_contrasenia=contrasenia.getText().toString();
        String[][] parametros=new String[][]
                {{"username",txt_usuario},
                {"password",txt_contrasenia}};
        Json json=c.sendRequest(Comunicacion.INICIAR_SESION,this,parametros);
        if(c.hasError()){
            resultado.setText(c.getError());
        }else if(json==null) {
            resultado.setText("El resultado es nulo");
        }else if(!json.hasError()){
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

    public void enviarAListado(View v){
        startActivity(new Intent(MainActivity.this, ListadoVehiculosActivity.class));
    }

}

