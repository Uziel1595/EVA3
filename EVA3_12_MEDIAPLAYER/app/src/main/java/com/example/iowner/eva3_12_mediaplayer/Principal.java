package com.example.iowner.eva3_12_mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Principal extends AppCompatActivity {

    MediaPlayer mpReproductor = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        mpReproductor = MediaPlayer.create(this,R.raw.come_on_feel_the_noise);

    }
    @Override
    protected void onStop() {
        super.onStop();
        if(mpReproductor!=null){
            mpReproductor.stop();
            mpReproductor.release();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mpReproductor!=null){
            mpReproductor.start();
        }
    }
}
