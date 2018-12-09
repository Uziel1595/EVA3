package com.example.iowner.eva3_4_banner;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class Principal extends AppCompatActivity {
    ImageView imageView;
    SeekBar barTiempo;
    Banner hilo;
    int tiempo = 2000;
    int control = 1;
    Handler hHand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int var1 = 2000;
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
            int var = barTiempo.getProgress();
            if(var==0)
                var = 1;
            tiempo = var1/var;
            }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        imageView = findViewById(R.id.imgView);
        barTiempo = findViewById(R.id.barTiempo);
        hilo = new Banner();
        hilo.start();
    }
    class Banner extends Thread{
        @Override
        public void run() {
            super.run();
            while(true){
                try {
                    Thread.sleep(tiempo);
                    Message msg = hHand.obtainMessage();
                    hHand.sendMessage(msg);
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
