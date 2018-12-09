package com.example.iowner.eva3_practica_clima;

/**
 * Created by iOwner on 29/11/2018.
 */

public class Clima {
    String nombreCiudad,descripcion;
    double temperatura;
    int imagen;
    public Clima(String nom,String des, double temp, int imag){
        nombreCiudad = nom;
        descripcion = des;
        temperatura = temp;
        imagen = imag;
    }
}
