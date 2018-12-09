package com.example.iowner.eva3_11_broadcastreceiver_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    TextView txt2;
    BroadcastReceiver miBroadCast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        txt2 = findViewById(R.id.txt2);

        miBroadCast = new MiBradCast();
        IntentFilter ifMiFiltro = new IntentFilter("MI_SERVICIOTE");
        registerReceiver(miBroadCast,ifMiFiltro);
    }
    class MiBradCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            txt2.append(intent.getStringExtra("MENSAJE"));
        }
    }
}
