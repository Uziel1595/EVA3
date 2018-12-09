package com.example.iowner.eva3_7_banner_post;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class Principal extends AppCompatActivity {
    ImageView imageView;
    Banner hilo;
    int tiempo = 2000;
    int control = 1;
    Handler hHand = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            switch(control){
                case 1:
                    imageView.setImageResource(R.drawable.cloudy);
                    control++;
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.rainy);
                    control++;
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.sunny);
                    control=1;
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        imageView = findViewById(R.id.imgView);
        hilo = new Banner();
        hilo.start();
    }
    class Banner extends Thread{
        @Override
        public void run() {
            super.run();
            while(true){
                try {
                    Thread.sleep(1000);
                    hHand.post(run);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        hilo.interrupt();
    }
}
