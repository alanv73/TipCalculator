package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Intent intent = new Intent(this, MainActivity.class);

        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                //start mainActivity
                startActivity(intent);

            }
        };

        Timer splash = new Timer();
        splash.schedule(task, 7000);
    }
}
