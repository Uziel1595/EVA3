package com.example.iowner.eva3_2_hilos;

import android.util.Log;

/**
 * Created by iOwner on 30/10/2018.
 */

public class MiHilo extends Thread {
    @Override
    public void run() {
        super.run();
        while (true) {
            Log.wtf("Hilo","Hola");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
