package com.example.iowner.eva3_6_handler_post;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    TextView txt;
    int iValor;
    Thread tHilo;
    Handler hManejador = new Handler();
    //RUNNABLE 1 --> TRABAJO PESADO EN SEGUNDO PLANO
    Runnable rSegundoPlano = new Runnable() {
        @Override
        public void run() {
            while(true) {
                hManejador.post(rRunnableUI);
                iValor++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    //RUNNABLW 2 --> TRABAJO LIGERO Y TRABAJO EN LA UI
    Runnable rRunnableUI = new Runnable() {
        @Override
        public void run() {
            //AQUI INTERACTUAMOS CON UI
            txt.append(iValor+" - ");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        txt = findViewById(R.id.txt);

        tHilo = new Thread(rSegundoPlano);
        tHilo.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        tHilo.interrupt();
    }
}
