package com.example.iowner.eva3_3_handlers;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    TextView txtEt;
    Thread tHilo;
    Handler hHand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //AQUI SE INTERACTUA CON INTERFAZ GRAFICA
            if(msg.what==1){
                int i = (int)msg.obj;
                txtEt.append(i+"-");
            }

        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        txtEt = findViewById(R.id.txtEt);
        MiHilo miHilo = new MiHilo();
        tHilo = new Thread(miHilo);
        tHilo.start();
        //HANDLER
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tHilo.interrupt();
    }

    class MiHilo implements Runnable{
        @Override
        public void run() {
            int i = 0;
            while(true){
                i++;
                try {
                    Message msg = hHand.obtainMessage(1,i);
                    hHand.sendMessage(msg);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }
}
