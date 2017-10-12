package com.example.christianescobar.myapplication;

/**
 * Created by kevinj on 11/10/17.
 */

public abstract class V {
    public static final byte INT = 0;
    public static final byte DOUBLE = 1;
    public static final byte STRING = 2;
    public static final byte DATE = 3;
    public static final String[] TIPOS={"entero", "decimal", "cadena", "fecha"};

    public static String SERVER="192.168.0.29";
    public static final String PUERTO="8093";

    public static int idVehiculo;
    public static String pais;

    public static String usuario;
    public static String noTarjeta;

    public static double precio_Vehiculo,precio_Envio,impuesto_Sat,impuesto_Aduana,taller,iva,isr;

}
