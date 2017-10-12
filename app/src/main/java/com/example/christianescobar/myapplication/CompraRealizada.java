package com.example.christianescobar.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CompraRealizada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_realizada);

        RequestQueue queue2 = Volley.newRequestQueue(CompraRealizada.this);
        String url="http://"+V.SERVER+":"+V.PUERTO+"/Importadora/comprar_Vehiculo";
        StringRequest putRequest2 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
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
                        TextView serie = (TextView)findViewById(R.id.content_serie);
                        serie.setText((String) json.getField("serie", V.STRING));
                        TextView numero_Factura = (TextView)findViewById(R.id.content_numero_Factura);
                        numero_Factura.setText((String) json.getField("numero_Factura", V.STRING));
                        TextError.setText("Se compro el vehiculo");
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
                params.put("username", V.usuario);
                params.put("no_Tarjeta",V.noTarjeta);
                params.put("id_Vehiculo", ""+V.idVehiculo);
                params.put("precio_Envio", ""+V.precio_Envio);
                params.put("impuesto_Sat", ""+V.impuesto_Sat);
                params.put("impuesto_Aduana", ""+V.impuesto_Aduana);
                params.put("iva", ""+V.iva);
                params.put("isr", ""+V.isr);
                params.put("pais_Destino", "Guatemala");
                return params;
            }
        };
        queue2.add(putRequest2);
    }
}
