package com.example.iowner.eva3_11_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    Intent inServicio;
    TextView txtEt;
    BroadcastReceiver miBroadCast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        txtEt = findViewById(R.id.txtEt);
        inServicio = new Intent(this, MiServicio.class);

        miBroadCast = new MiBradCast();
        IntentFilter ifMiFiltro = new IntentFilter("MI_SERVICIOTE");
        registerReceiver(miBroadCast,ifMiFiltro);
    }
    public void clickIni(View v){
        startService(inServicio);
    }
    public void clickFin(View v){
        stopService(inServicio);
    }
    class MiBradCast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            txtEt.append(intent.getStringExtra("MENSAJE"));
        }
    }
}
