package com.adam.iptv;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import io.vov.vitamio.R;

/**
 * Created by adam4325 on 1/23/15.
 */
public class SplashScreen extends Activity {
private static int SPLASH_TIME_OUT=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent in = new Intent(SplashScreen.this,MainActivity.class);
//                startActivity(in);
//                finish();
//            }
//        },SPLASH_TIME_OUT);

        Thread bg = new Thread(){
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(5*1000);

                    // After 5 seconds redirect to another intent
                    Intent i=new Intent(SplashScreen.this,VideoRTMP.class);
                    startActivity(i);

                    //Remove activity
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        bg.start();
    }
}
