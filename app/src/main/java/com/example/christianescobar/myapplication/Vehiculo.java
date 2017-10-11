package com.example.christianescobar.myapplication;

/**
 * Created by chris on 11/10/17.
 */

public class Vehiculo {
    int id_Vehiculo;
    String marca;
    String linea;
    int modelo;
    String pais_Origen;
    double precio_Vehiculo;

    public Vehiculo(int id_Vehiculo, String marca, String linea, int modelo, String pais_Origen, double precio_Vehiculo) {
        this.id_Vehiculo = id_Vehiculo;
        this.marca = marca;
        this.linea = linea;
        this.modelo = modelo;
        this.pais_Origen = pais_Origen;
        this.precio_Vehiculo = precio_Vehiculo;
    }

    public int getId_Vehiculo() {
        return id_Vehiculo;
    }

    public void setId_Vehiculo(int id_Vehiculo) {
        this.id_Vehiculo = id_Vehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getPais_Origen() {
        return pais_Origen;
    }

    public void setPais_Origen(String pais_Origen) {
        this.pais_Origen = pais_Origen;
    }

    public double getPrecio_Vehiculo() {
        return precio_Vehiculo;
    }

    public void setPrecio_Vehiculo(double precio_Vehiculo) {
        this.precio_Vehiculo = precio_Vehiculo;
    }
}
