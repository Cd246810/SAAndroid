package com.example.christianescobar.myapplication;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void Json() throws Exception {
        Json json=new Json("{\n" +
                "\"nombre\" : \"\",\n" +
                "\"no_Tarjeta\" : \"\",\n" +
                "\"status\" : 1,\n" +
                "\"descripcion\" : \"Usuario o contraseña incorrectos.\"\n" +
                "}\n");
        assertEquals(4, 2 + 2);
    }
}