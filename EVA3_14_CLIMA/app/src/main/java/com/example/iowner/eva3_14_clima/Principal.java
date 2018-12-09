package com.example.iowner.eva3_14_clima;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    TextView txtDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        txtDatos = findViewById(R.id.txt1);
    }
    public void onClick(View v){
        Conexion con = new Conexion();
        con.execute();
    }
    class Conexion extends AsyncTask<Void,Void,String>{
        final String sLink = "http://api.openweathermap.org/data/2.5/box/city?bbox=-106,21,-104,28,10&appid=6d3f28d706799a6bcb2b1785529fbfb7";

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
                    for(int i= 0;i<jCiudades.length();i++){
                       JSONObject jCiudad = jCiudades.getJSONObject(i);
                       txtDatos.append("Ciudad: "+jCiudad.getString("name"));
                    }
                }
                catch (Exception e){

                }
            }
            else{
                txtDatos.setText(s);
            }
        }
    }
}


