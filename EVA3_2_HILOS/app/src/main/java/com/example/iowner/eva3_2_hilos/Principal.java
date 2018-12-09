package com.example.iowner.eva3_2_hilos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        txt = findViewById(R.id.txt);

        Runnable rHilo = new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        txt.setText("Hola");
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        Thread tHilo = new Thread(rHilo);
        tHilo.start();
        MiHilo mhPerpetuo = new MiHilo();
        mhPerpetuo.start();
    }
}
