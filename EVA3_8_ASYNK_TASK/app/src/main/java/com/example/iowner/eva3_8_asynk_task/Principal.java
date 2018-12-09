package com.example.iowner.eva3_8_asynk_task;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

    TextView txtDatos;
    MiClaseAsincrona miTareaAsincrono;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        txtDatos = findViewById(R.id.txtDatos);
        miTareaAsincrono = new MiClaseAsincrona();
        miTareaAsincrono.execute("Hola","Mundo");
    }

    class MiClaseAsincrona extends AsyncTask<String,Integer,Void> {
        String sCade;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtDatos.setText(sCade);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            txtDatos.append(i+" - ");
        }
        //UNICO METODO QUE NO PUEDE UTILIZAR UI
        @Override
        protected Void doInBackground(String... strings) {
            sCade = strings[0] + " " + strings[1];
            while(true){
                try{
                    Thread.sleep(1000);
                    publishProgress(i++);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        miTareaAsincrono.cancel(true);
    }
}
