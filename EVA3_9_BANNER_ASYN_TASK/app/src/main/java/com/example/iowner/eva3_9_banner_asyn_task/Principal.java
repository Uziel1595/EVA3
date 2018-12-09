package com.example.iowner.eva3_9_banner_asyn_task;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Principal extends AppCompatActivity {

    int control = 1;
    BannerAsyn miBanner;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        img = findViewById(R.id.imageView);

        miBanner = new BannerAsyn();
        miBanner.execute();
    }
    class BannerAsyn extends AsyncTask<Integer,Integer,Integer>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            switch(control){
                case 1:
                    img.setImageResource(R.drawable.cloudy);
                    break;
                case 2:
                    img.setImageResource(R.drawable.rainy);
                    break;
                case 3:
                    img.setImageResource(R.drawable.sunny);
                    control=1;
                    break;
            }
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            while(true){
                try {
                    Thread.sleep(1000);
                    publishProgress(control++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        miBanner.cancel(true);
    }
}
