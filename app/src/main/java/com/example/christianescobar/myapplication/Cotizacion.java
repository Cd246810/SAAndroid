package com.example.christianescobar.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Cotizacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizacion);
        Button btn = (Button)findViewById(R.id.button);

        RequestQueue queue = Volley.newRequestQueue(Cotizacion.this);
        String url="http://"+V.SERVER+":"+V.PUERTO+"/Importadora/cotizar_Vehiculo";
        StringRequest putRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                TextView TextError = (TextView)findViewById(R.id.error);
                //TextError.setText(response);
                Json json=new Json(response);
                //status, descripcion
                if(!json.hasError()){
                    int estado = (int) json.getField("status",V.INT);
                    if(estado!=0){
                        TextError.setText((String)json.getField("descripcion",V.STRING));
                    }else{
                        TextView content_precio_Vehiculo = (TextView)findViewById(R.id.content_precio_Vehiculo);
                        content_precio_Vehiculo.setText("Q"+(double)json.getField("precio_Vehiculo",V.DOUBLE));
                        TextView content_precio_Envio = (TextView)findViewById(R.id.content_precio_Envio);
                        content_precio_Envio.setText("Q"+(double)json.getField("precio_Envio",V.DOUBLE));
                        TextView content_precio_sat = (TextView)findViewById(R.id.content_precio_sat);
                        content_precio_sat.setText("Q"+(double)json.getField("impuesto_Sat",V.DOUBLE));
                        TextView content_impuesto_Aduana = (TextView)findViewById(R.id.content_impuesto_Aduana);
                        content_impuesto_Aduana.setText("Q"+(double)json.getField("impuesto_Aduana",V.DOUBLE));
                        TextView content_taller = (TextView)findViewById(R.id.content_taller);
                        content_taller.setText("Q"+(double)json.getField("taller",V.DOUBLE));
                        TextView content_iva = (TextView)findViewById(R.id.content_iva);
                        content_iva.setText("Q"+(double)json.getField("iva",V.DOUBLE));
                        TextView content_isr = (TextView)findViewById(R.id.content_isr);
                        content_isr.setText("Q"+(double)json.getField("isr",V.DOUBLE));
                        TextError.setText("");
                    }
                }else{
                    TextError.setText("No se pudo parsear la respuesta: "+response);
                }
            }},new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                TextView TextError = (TextView)findViewById(R.id.error);
                TextError.setText("No se pudo conectar al bus de integracion.");
            }
        }) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_Vehiculo", "11");
                params.put("pais_Destino", "Guatemala");
                return params;
            }
        };
        queue.add(putRequest);


        //error.setText("");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
