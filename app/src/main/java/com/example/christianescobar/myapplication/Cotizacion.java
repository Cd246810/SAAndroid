package com.example.christianescobar.myapplication;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                        V.precio_Vehiculo=(double)json.getField("precio_Vehiculo",V.DOUBLE);
                        content_precio_Vehiculo.setText("Q"+V.precio_Vehiculo);
                        TextView content_precio_Envio = (TextView)findViewById(R.id.content_precio_Envio);
                        V.precio_Envio=(double)json.getField("precio_Envio",V.DOUBLE);
                        content_precio_Envio.setText("Q"+V.precio_Envio);
                        TextView content_precio_sat = (TextView)findViewById(R.id.content_precio_sat);
                        V.impuesto_Sat=(double)json.getField("impuesto_Sat",V.DOUBLE);
                        content_precio_sat.setText("Q"+V.impuesto_Sat);
                        TextView content_impuesto_Aduana = (TextView)findViewById(R.id.content_impuesto_Aduana);
                        V.impuesto_Aduana=(double)json.getField("impuesto_Aduana",V.DOUBLE);
                        content_impuesto_Aduana.setText("Q"+V.impuesto_Aduana);
                        TextView content_taller = (TextView)findViewById(R.id.content_taller);
                        V.taller=(double)json.getField("taller",V.DOUBLE);
                        content_taller.setText("Q"+V.taller);
                        TextView content_iva = (TextView)findViewById(R.id.content_iva);
                        V.iva=(double)json.getField("iva",V.DOUBLE);
                        content_iva.setText("Q"+V.iva);
                        TextView content_isr = (TextView)findViewById(R.id.content_isr);
                        V.isr=(double)json.getField("isr",V.DOUBLE);
                        content_isr.setText("Q"+V.isr);
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

                startActivity(new Intent(Cotizacion.this, CompraRealizada.class));



                //try {

                    //Date date = new Date() ;
                    //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);
                    //File directory = new File(Environment.getExternalStorageDirectory()+File.separator+"pdfSA");

                    //if (!pdfFolder.exists()) {
                    //    pdfFolder.mkdir();
                    //}

                    //    directory.mkdirs();
                    /*
                    TextView TextError = (TextView)findViewById(R.id.error);
                    TextError.setText(""+directory.getAbsolutePath());
                    */
                    //Create time stamp


                    //File myFile = new File("/storage/emulated/0/" + timeStamp + ".pdf");

                    //OutputStream output = new FileOutputStream(pdfFolder);

                    //Step 1
                    //Document document = new Document();

                    //Step 2
                    //PdfWriter.getInstance(document, output);

                    //Step 3
                    //document.open();

                    //Step 4 Add content
                    //document.add(new Paragraph("Puede funcionar"));
                    //document.add(new Paragraph("No, no puede?"));

                    //Step 5: Close the document
                    //document.close();
                    //Uri path = Uri.fromFile(myFile);
                    //Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                    //pdfIntent.setDataAndType(path, "application/pdf");
                    //pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    //try {
                    //    startActivity(pdfIntent);
                    //} catch (ActivityNotFoundException e) {
                    //    TextView TextError = (TextView)findViewById(R.id.error);
                    //    TextError.setText("Can't read pdf file");
                    //    Toast.makeText(Cotizacion.this, "Can't read pdf file", Toast.LENGTH_SHORT).show();
                   // }

                //}catch (DocumentException e) {
                //    TextView TextError = (TextView)findViewById(R.id.error);
                //    TextError.setText(e.toString()+"Es aqui");
                //}catch (IOException e) {
                //    TextView TextError = (TextView)findViewById(R.id.error);
                //    TextError.setText(e.toString());
                //}
            }
        });

    }

}
