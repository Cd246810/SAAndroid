package com.example.christianescobar.myapplication;

//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 *
 * @author kevinj
 */
public class Json {

    private JSONObject data;
    private boolean error=false;
    private String txt_error = "";

    public Json(String jsonText) {
        try{
            data = new JSONObject(jsonText);
        }catch(Exception ex){
            txt_error = "No se pudo crear el objeto JSON con el texto: "+jsonText;
            error=true;
            data = null;
        }
    }

    public Object getField(String tag, byte tipo) {
        if (data == null) {
            error=true;
            return null;
        }
        Object o=null;
        try{
            o= data.get(tag);
        }catch(JSONException ex){
            txt_error="No se encontró el tag solicitado: '"+tag+"'";
            error=true;
            return null;
        }
        try {
            switch (tipo) {
                case V.INT:
                    o = (int) o;
                    break;
                case V.DOUBLE:
                    o = (double) o;
                    break;
                case V.STRING:
                    o = (String) o;
                    break;
                case V.DATE:
                    o = (java.util.Date) o;
                    break;
            }
        }catch(ClassCastException ex){
            error=true;
            txt_error="No se pudo convertir el dato a "+V.TIPOS[tipo];
        }
        return o;
    }

    public boolean hasError(){
        return error;
    }
    public String getError(){
        return txt_error;
    }
}