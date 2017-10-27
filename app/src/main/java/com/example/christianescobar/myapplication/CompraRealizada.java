package com.example.christianescobar.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
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

public class CompraRealizada extends AppCompatActivity {


    String numSerie="";
    String numFactura="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_realizada);
        setTitle("Detalle del vehiculo");
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
                        numSerie = (String) json.getField("serie", V.STRING);
                        serie.setText(numSerie);
                        TextView numero_Factura = (TextView)findViewById(R.id.content_numero_Factura);
                        numFactura = (String) json.getField("numero_Factura", V.STRING);
                        numero_Factura.setText(numFactura);
                        TextError.setText("Se compro el vehiculo");


                        try {

                            Date date = new Date() ;
                            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);
                            File directory = new File(Environment.getExternalStorageDirectory()+File.separator+"pdfSA");

                            if (!directory.exists()) {
                                directory.mkdir();
                            }

                            directory.mkdirs();
                    /*
                    TextView TextError = (TextView)findViewById(R.id.error);
                    TextError.setText(""+directory.getAbsolutePath());
                    */
                            //Create time stamp


                            File myFile = new File(directory+"/" + timeStamp + ".pdf");

                            OutputStream output = new FileOutputStream(myFile);

                            //Step 1
                            Document document = new Document();

                            //Step 2
                            PdfWriter.getInstance(document, output);

                            //Step 3
                            document.open();

                            //Step 4 Add content
                            //document.add(new Paragraph("Puede funcionar"));
                            //document.add(new Paragraph("No, no puede?"));
                            document.add(new Paragraph("Numero de serie: "+numSerie));
                            document.add(new Paragraph("Numero de factura: "+numFactura));
                            document.add(new Paragraph("Costo del vehículo: "+V.precio_Vehiculo+"\n"
                                    +"Costo de envío: "+V.precio_Envio+"\n"
                                    +"Impuesto SAT: "+V.impuesto_Sat+"\n" +
                                    ""+"Impuesto Aduana: "+V.impuesto_Aduana+"\n"
                                    +"Costo del taller: "+V.taller+"\n"
                                    +"Costo del IVA: "+V.iva+"\n"
                                    +"Costo del ISR: "+V.isr));

                            //Step 5: Close the document
                            document.close();
                            Uri path = Uri.fromFile(myFile);
                            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                            pdfIntent.setDataAndType(path, "application/pdf");
                            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            try {
                                startActivity(pdfIntent);
                            } catch (ActivityNotFoundException e) {
                                TextError = (TextView)findViewById(R.id.error);
                                TextError.setText("Can't read pdf file");
                                Toast.makeText(CompraRealizada.this, "Can't read pdf file", Toast.LENGTH_SHORT).show();
                            }

                        }catch (DocumentException e) {
                            TextError = (TextView)findViewById(R.id.error);
                            TextError.setText(e.toString()+"Es aqui");
                        }catch (IOException e) {
                            TextError = (TextView)findViewById(R.id.error);
                            TextError.setText(e.toString());
                        }
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
