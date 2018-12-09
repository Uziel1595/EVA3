package com.example.iowner.eva3_13_mediaplayer_servicio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity {
    Intent inServici;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        inServici = new Intent(this, MiServicio.class);

    }
    public void clickIni(View v){
        startService(inServici);
    }
    public void clickFin(View v){
        stopService(inServici);
    }
}
