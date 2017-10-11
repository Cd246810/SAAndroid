package com.example.christianescobar.myapplication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kevinj on 11/10/17.
 */

public class Comunicacion{


    private Json respuesta;
    private boolean error;
    private String txt_error;

    private boolean esperar=true;

    public static final String CREAR_CUENTA = "crear_Cuenta";
    public static final String INICIAR_SESION = "validar_Sesion";

    public Comunicacion(){
        respuesta=null;
        error=false;
        txt_error="";
    }

    /*
        Se envía la solicitud especificando el metodo, por ejemplo "crear_Cuenta".
        El contexto es el "this" de la Activity.
        Los parametros son una matriz estática con el contenido organizado de la siguiente manera:
            [
            ["usuario","id_usuario"],
            ["contrasenia","c0ntras3nia"]
            ]

     */
    public Json sendRequest(String metodo, Context contexto, final String[][] parametros){
        RequestQueue queue = Volley.newRequestQueue(contexto);
        if (contexto == null) {
            error = true;
            txt_error = "El contexto es nulo";
            return null;
        } else if (parametros != null && parametros.length != 0 && parametros[0].length == 2) {
            String url = "http://" + V.SERVER + ":" + V.PUERTO + "/Importadora/" + metodo;
            StringRequest putRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    respuesta = new Json(response);
                    if(respuesta.hasError()){
                        error=true;
                        txt_error=respuesta.getError();
                    }
                    esperar=false;
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Comunicacion.this.error = true;
                    txt_error = "Error de conexión a " + V.SERVER;
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
            try {
                //queue.wait();
                while(esperar)Thread.sleep(300);
            } catch (Exception e) {
                error = true;
                txt_error = "Error esperando que termine la solicitud a Importadora";
            }
        } else {
            error = true;
            txt_error = "El formato de los parámetros es incorrecto. Debe de tener una lista de de parametros en parejas";
        }
        return respuesta;
    }

    public boolean hasError(){
        return error;
    }
    public String getError(){
        return txt_error;
    }

}
