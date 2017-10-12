package com.example.christianescobar.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText contrasenia;
    private EditText nombre;
    private EditText tarjeta;

    private TextView resultado;
    private TextView txt_nombre;
    private TextView txt_tarjeta;

    private Button btn_ingreso;
    private Button btn_registro;

    private Button lnk_registro;
    private Button lnk_ingreso;

    private boolean stateRegistro=false;

    public static final String CREAR_CUENTA = "crear_Cuenta";
    public static final String INICIAR_SESION = "validar_Sesion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Inicio de Sesión");
        resultado=(TextView) findViewById(R.id.txt_Resultado);
        txt_nombre=(TextView) findViewById(R.id.txt_askNombre);
        txt_tarjeta=(TextView) findViewById(R.id.txt_askTarjeta);

        usuario=(EditText) findViewById(R.id.txt_Usuario);
        contrasenia=(EditText) findViewById(R.id.txt_Contrasenia);
        nombre=(EditText) findViewById(R.id.txt_Nombre);
        tarjeta=(EditText) findViewById(R.id.txt_Tarjeta);

        btn_registro=(Button) findViewById(R.id.btn_Registro);
        btn_ingreso=(Button) findViewById(R.id.btn_Ingreso);

        lnk_ingreso=(Button)findViewById(R.id.lnk_InicioSesion);
        lnk_registro=(Button)findViewById(R.id.lnk_Registro);
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

    public void registrar(View v){
        String txt_usuario= usuario.getText().toString();
        String txt_contrasenia=contrasenia.getText().toString();
        String txt_nombre=nombre.getText().toString();
        String txt_tarjeta=tarjeta.getText().toString();
        String[][] parametros=new String[][]{
                {"nombre",txt_nombre},
                {"username",txt_usuario},
                {"password",txt_contrasenia},
                {"no_Tarjeta",txt_tarjeta}
        };
        sendRequest(CREAR_CUENTA,parametros);
    }

    public void cambiarVista(View v){
        int visibility = stateRegistro? View.GONE:View.VISIBLE;
        int visibility2= (!stateRegistro)? View.GONE:View.VISIBLE;
        txt_tarjeta.setVisibility(visibility);
        txt_nombre.setVisibility(visibility);
        tarjeta.setVisibility(visibility);
        nombre.setVisibility(visibility);
        btn_registro.setVisibility(visibility);
        lnk_ingreso.setVisibility(visibility);

        lnk_registro.setVisibility(visibility2);
        btn_ingreso.setVisibility(visibility2);
        resultado.setText("");
        stateRegistro=(!stateRegistro);
    }

    public void sendRequest(String metodo, final String[][] parametros){
        resultado.setTextColor(Color.RED);
        RequestQueue queue = Volley.newRequestQueue(this);
        if (parametros != null && parametros.length != 0 && parametros[0].length == 2) {
            String url = "http://" + V.SERVER + ":" + V.PUERTO + "/Importadora/" + metodo;
            final String tipo=metodo;
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
                                //INGRESO DE SESIÓN
                                if(tipo.equals(INICIAR_SESION)) {
                                    resultado.setText("Usuario ingresado.");
                                    resultado.setTextColor(Color.BLUE);
                                    V.nombre = (String) json.getField("nombre", V.STRING);
                                    V.noTarjeta = (String) json.getField("no_Tarjeta", V.STRING);
                                }
                                //REGISTRO DE USUARIO
                                else{
                                    resultado.setText("Usuario Creado");
                                    resultado.setTextColor(Color.BLUE);
                                    String txt_nombre=nombre.getText().toString();
                                    String txt_tarjeta=tarjeta.getText().toString();
                                }
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


