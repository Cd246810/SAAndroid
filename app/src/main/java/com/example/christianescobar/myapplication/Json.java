package com.example.christianescobar.myapplication;

/**
 * Created by christianescobar on 11/10/17.
 */

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

/**
 *
 * @author kevinj
 */
public class Json {

    public static final byte INT = 0;
    public static final byte DOUBLE = 1;
    public static final byte STRING = 2;
    public static final byte DATE = 3;

    JSONObject data;

    public Json(String jsonText) throws JSONException {
        data = new JSONObject(jsonText);
    }

    public Object getField(String tag, int tipo) throws JSONException {
        if (data == null) {
            return null;
        }
        Object o = data.get(tag);
        switch (tipo) {
            case INT:
                o = (int) o;
                break;
            case DOUBLE:
                o = (double) o;
                break;
            case STRING:
                o = (String) o;
                break;
            case DATE:
                o = (java.util.Date) o;
                break;
        }
        return o;
    }
}