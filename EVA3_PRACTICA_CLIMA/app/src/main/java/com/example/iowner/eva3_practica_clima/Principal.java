package com.example.iowner.eva3_practica_clima;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Principal extends AppCompatActivity {
    Clima[] climas;
    ListView list;
    Intent iClima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        list = findViewById(R.id.list);
        Conexion con = new Conexion();//ejecucion de la conexion a la API
        con.execute();
    }
    //Metodo para llenar lista
    public void llenarLista(){
        list.setAdapter(new ClimaAdapter(this,R.layout.layout_clima,climas));
    }




    class Conexion extends AsyncTask<Void,Void,String> {
        final String sLink = "http://api.openweathermap.org/data/2.5/box/city?bbox=-110,23,-100,33,10&appid=6d3f28d706799a6bcb2b1785529fbfb7";

        @Override
        protected String doInBackground(Void... voids) {
            String sResu = "";
            try {
                URL url = new URL(sLink);
                HttpURLConnection httpCon = (HttpURLConnection)url.openConnection();
                if(httpCon.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader brDatos = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                    sResu = brDatos.readLine();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sResu;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONArray jCiudades = null;
            if(!s.equals(""))
            {
                try {
                    JSONObject jsDatos = new JSONObject(s);
                    jCiudades = jsDatos.getJSONArray("list");
                    climas = new Clima[jCiudades.length()-1];//definimos tamano del arreglo utilizando el tamano del arreglo json
                    for(int i= 0;i<jCiudades.length();i++){
                        JSONObject jCiudad = jCiudades.getJSONObject(i);
                        //Obtenemos array weather del json
                        JSONArray jClima = jCiudad.getJSONArray("weather");
                        JSONObject jClimas = jClima.getJSONObject(0);//Seleccionamos el primer elemento del arreglo(id de climea)
                        String sNombre = jCiudad.getString("name");
                        //comparamos codigo y asignamos imagenes
                        int iWeather = jClimas.getInt("id");//obtenemos codigo
                        String sDescripcion = "";
                        int iImage = 0;
                        if(iWeather==800)
                        {
                            sDescripcion = "Soleado";
                            iImage = R.drawable.sunny;
                        }
                        else
                        {
                            if(iWeather<300)
                            {
                                sDescripcion = "Tormenta Electrica";
                                iImage = R.drawable.thunderstorm;
                            }
                            else
                            {
                                if(iWeather<500)
                                {
                                    sDescripcion = "Chipi Chipi";
                                    iImage = R.drawable.light_rain;
                                }
                                else
                                {
                                    if(iWeather<600)
                                    {
                                        sDescripcion = "Lloviendo";
                                        iImage = R.drawable.rainy;
                                    }
                                    else
                                    {
                                        if(iWeather<700)
                                        {
                                            sDescripcion = "Nevando";
                                            iImage = R.drawable.snow;
                                        }
                                        else
                                        {
                                            if(iWeather<800)
                                            {
                                                if(iWeather == 781)
                                                {
                                                    sDescripcion = "Tornado!!!";
                                                    iImage = R.drawable.thunderstorm;
                                                }
                                                else
                                                {
                                                    sDescripcion = "Niebla";
                                                    iImage = R.drawable.atmospher;
                                                }
                                            }
                                            else
                                            {
                                                sDescripcion = "Nublado";
                                                iImage = R.drawable.cloudy;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        //Obtenemos el obje json "main"
                        JSONObject jMain = jCiudad.getJSONObject("main");
                        //Obtenemos temperatura del json main
                        Double dTemperatura = jMain.getDouble("temp");
                        //creamos elemento clima con datos obtenidos del json
                        climas[i]=new Clima(sNombre,sDescripcion,dTemperatura,iImage);
                    }
                }
                catch (Exception e){

                }
            }
            else{
                Toast.makeText(Principal.this, "Encuentradme", Toast.LENGTH_SHORT).show();
            }
            llenarLista();
        }
    }
}
