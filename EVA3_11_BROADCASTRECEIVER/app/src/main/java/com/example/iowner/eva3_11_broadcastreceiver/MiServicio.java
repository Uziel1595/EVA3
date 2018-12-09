package com.example.iowner.eva3_11_broadcastreceiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MiServicio extends Service {
    Thread tHilo;
    Intent inDatos;
    public MiServicio() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        inDatos = new Intent("MI_SERVICIOTE");
        inDatos.putExtra("MENSAJE","START!!");
        sendBroadcast(inDatos);
        Runnable rnHilo = new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                        Log.wtf("MiServicio","Hola!!!!");
                        inDatos = new Intent("MI_SERVICIOTE");
                        inDatos.putExtra("MENSAJE","HOLA!!!");
                        sendBroadcast(inDatos);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        tHilo = new Thread(rnHilo);
        tHilo.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tHilo.interrupt();
        inDatos = new Intent("MI_SERVICIOTE");
        inDatos.putExtra("MENSAJE","ADIOS!!!");
        sendBroadcast(inDatos);
    }

}
