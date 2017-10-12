package com.example.christianescobar.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.id.list;

public class ListadoVehiculosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_vehiculos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

        //Vehiculo vh = new Vehiculo(11, "TOYOTA", "hilux", 2011, "Estados Unidos", 35000);
        //Vehiculo vh2 = new Vehiculo(12, "TOYOTA", "hilux", 2011, "Estados Unidos", 35000);

        final ListView lv = (ListView) findViewById(R.id.listView);


        RequestQueue queue = Volley.newRequestQueue(ListadoVehiculosActivity.this);
        String url="http://"+V.SERVER+":"+V.PUERTO+"/Importadora/solicitar_Catalogo_Vehiculos";
        StringRequest putRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                System.out.println("CATALOGO: " + response);

                try{
                    JSONObject reader = new JSONObject(response);

                    int status = reader.getInt("status");

                    if(status==0){
                        JSONArray array_Vehiculos = reader.getJSONArray("vehiculos");

                        for(int i=0; i<=array_Vehiculos.length()-1; i++){
                            JSONObject vehiculo = array_Vehiculos.getJSONObject(i);

                            int id_Vehiculo = vehiculo.getInt("id_Vehiculo");
                            String marca = vehiculo.getString("marca");
                            String linea = vehiculo.getString("linea");
                            int modelo = vehiculo.getInt("modelo");
                            String pais_Origen = vehiculo.getString("pais_Origen");
                            double precio_Vehiculo = vehiculo.getDouble("precio_Vehiculo");

                            Vehiculo vh = new Vehiculo(id_Vehiculo, marca, linea, modelo, pais_Origen, precio_Vehiculo);
                            vehiculos.add(vh);



                        }

                        AdapterItem adapter = new AdapterItem(ListadoVehiculosActivity.this, vehiculos);

                        lv.setAdapter(adapter);

                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                final int pos = position;
                                System.out.println("CLIC EN ITEM");

                            }
                        });

                    }
                    else{
                        String descripcion = reader.getString("descripcion");
                        Toast.makeText(ListadoVehiculosActivity.this, "Error al obtener catalogo, descripcion: " + descripcion, Toast.LENGTH_LONG).show();
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            }},new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                //TextView TextError = (TextView)findViewById(R.id.error);
                //TextError.setText("No se pudo conectar al bus de integracion.");
                System.out.println("ERROR SOLICITANDO CATALOGO");
            }
        }) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                //params.put("id_Vehiculo", "11");
                //params.put("pais_Destino", "Guatemala");
                return params;
            }
        };
        queue.add(putRequest);

    }

}
