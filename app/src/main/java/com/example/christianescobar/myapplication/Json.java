package com.example.christianescobar.myapplication;

/**
 * Created by christianescobar on 11/10/17.
 */

        //import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

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
        }catch(JSONException ex){
            txt_error = "No se pudo crear el objeto JSON";
            error=true;
            data = null;
        }
    }

    public Object getField(String tag, int tipo) {
        if (data == null) {
            error=true;
            return null;
        }
        Object o;
        try{
            o= data.get(tag);
        }catch(JSONException ex){
            txt_error="No se encontró el tag solicitado: '"+tag+"'";
            error=true;
            return null;
        }
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
        return o;
    }

    public boolean hasError(){
        return error;
    }
    public String getError(){
        return txt_error;
    }
}