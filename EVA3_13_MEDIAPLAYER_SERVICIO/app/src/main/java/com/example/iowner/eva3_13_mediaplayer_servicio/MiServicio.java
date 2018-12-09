package com.example.iowner.eva3_13_mediaplayer_servicio;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by iOwner on 23/11/2018.
 */

public class MiServicio extends Service {
    MediaPlayer cancion;
    public MiServicio() {
        cancion = MediaPlayer.create(this,R.raw.come_on_feel_the_noise);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        cancion = MediaPlayer.create(this,R.raw.come_on_feel_the_noise);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Toast.makeText(this, "AA", Toast.LENGTH_SHORT).show();
        if(cancion != null){
            cancion.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(cancion != null){
            cancion.stop();
            cancion.release();
        }
    }

}
