package com.example.christianescobar.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        final TextView TV=(TextView) findViewById(R.id.id_value);
        TV.setText("Hola");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url="http://192.168.0.29:8093/Importadora/crear_Cuenta";
        //
        StringRequest putRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                TV.setText(response);
            }},new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                TV.setText("Error");
            }
            }) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("nombre", "Chriss");
                params.put("username", "Chriss");
                params.put("password", "A1234");
                params.put("no_Tarjeta", "A1234");
                return params;
            }
        };
        queue.add(putRequest);


    }
}