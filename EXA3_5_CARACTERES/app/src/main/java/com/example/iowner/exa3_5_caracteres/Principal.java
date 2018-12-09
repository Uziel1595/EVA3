package com.example.iowner.exa3_5_caracteres;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    EditText editTxtCarac;
    ConteoDeCaracteres miHilo;
    TextView txtNumeros;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String caracteres = editTxtCarac.getText().toString();
            int numero = caracteres.length();
            txtNumeros.setText("Hay "+numero+" caracteres.");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        editTxtCarac = findViewById(R.id.editTxtCarac);
        txtNumeros = findViewById(R.id.txtNumero);

        miHilo = new ConteoDeCaracteres();
        miHilo.start();
    }
    class ConteoDeCaracteres extends Thread
    {
        @Override
        public void run() {
            super.run();
            while(true){
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        miHilo.interrupt();
    }
}
