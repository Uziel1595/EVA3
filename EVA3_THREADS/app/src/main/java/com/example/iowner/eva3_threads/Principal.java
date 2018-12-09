package com.example.iowner.eva3_threads;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Principal extends AppCompatActivity {
    //RNNABLE
    Runnable rMiHiloRun = new Runnable() {
        @Override
        public void run() {
            for(int i = 0; i <20; i++){
                Log.wtf("Runnable",i+"");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //RUNNABLE(interfaz) Y THREAD(class)
        //RUN
        Thread tHilo = new Thread(rMiHiloRun);
        tHilo.start();
        MiHilo tHilo2 = new MiHilo();
        tHilo2.start();
    }

    class MiHilo extends Thread{
        @Override
        public void run() {
            super.run();
            for(int i = 0; i <20; i++){
                Log.wtf("Thread",i+"");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
