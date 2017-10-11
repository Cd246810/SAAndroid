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

    private boolean stateRegistro=false;

    public static final String CREAR_CUENTA = "crear_Cuenta";
    public static final String INICIAR_SESION = "validar_Sesion";

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
        resultado.setText("");
        resultado.setTextColor(Color.RED);
        String txt_usuario= usuario.getText().toString();
        String txt_contrasenia=contrasenia.getText().toString();
        String[][] parametros=new String[][]
                {{"username",txt_usuario},
                {"password",txt_contrasenia}};
        sendRequest(INICIAR_SESION,parametros);
    }


    public void enviarAListado(View v){
        startActivity(new Intent(MainActivity.this, Cotizacion.class));
    }

    public void sendRequest(String metodo, final String[][] parametros){
        RequestQueue queue = Volley.newRequestQueue(this);
        if (parametros != null && parametros.length != 0 && parametros[0].length == 2) {
            String url = "http://" + V.SERVER + ":" + V.PUERTO + "/Importadora/" + metodo;
            StringRequest putRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Json json = new Json(response);
                    if (json.hasError()) {
                        resultado.setText(json.getError());
                    } else {
                        Object o;
                        o = json.getField("status", V.INT);
                        if (json.hasError() || o == null) {
                            resultado.setText(json.getError());
                        } else {
                            int estado = (int) o;
                            if (estado != 0) {
                                resultado.setText((String) json.getField("descripcion", V.STRING));
                            } else {
                                resultado.setText("Usuario ingresado.");
                                resultado.setTextColor(Color.BLUE);
                                startActivity(new Intent(MainActivity.this, ListadoVehiculosActivity.class));
                            }
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    resultado.setText("Error de conexión a " + V.SERVER);
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    for (String[] parametro : parametros) {
                        params.put(parametro[0], parametro[1]);
                    }
                    return params;
                }
            };
            queue.add(putRequest);
        }else{
            resultado.setText("Los parmámetros no están bien definidos");
        }
    }
}

