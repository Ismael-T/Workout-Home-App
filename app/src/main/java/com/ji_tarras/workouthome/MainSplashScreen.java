package com.ji_tarras.workouthome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainSplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash_screen);
        getSupportActionBar().hide();
        new Handler().postDelayed(() -> {
            Intent i = new Intent(MainSplashScreen.this, MainActivity.class);
            startActivity(i);
            finish();
        }, 2500);
    }
}