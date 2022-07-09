package com.ji_tarras.workouthome;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class WeeklyProgress extends AppCompatActivity {
    int total, intense, moderate, light;
    TextView totW, intW, modW, ligW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_progress);
        Objects.requireNonNull(getSupportActionBar()).hide();

        SharedPreferences sp;

        totW = findViewById(R.id.totW);
        ligW = findViewById(R.id.ligW);
        modW = findViewById(R.id.modW);
        intW = findViewById(R.id.intW);

        Calendar reset = Calendar.getInstance();
        reset.set(Calendar.DAY_OF_WEEK, 2);
        reset.set(Calendar.HOUR_OF_DAY, 0);
        reset.set(Calendar.MINUTE, 0);
        reset.set(Calendar.SECOND, 0);
        reset.set(Calendar.MILLISECOND, 0);
        Date r = reset.getTime();
        Date c = Calendar.getInstance().getTime();

        Log.d("log", c.toString());
        Log.d("log", r.toString());

        sp = getSharedPreferences("progress_sp", Context.MODE_PRIVATE);
        total = sp.getInt("tw", 0);
        light = sp.getInt("lw", 0);
        moderate = sp.getInt("mw", 0);
        intense = sp.getInt("iw", 0);

        totW.setText(String.valueOf(total));
        ligW.setText(String.valueOf(light));
        modW.setText(String.valueOf(moderate));
        intW.setText(String.valueOf(intense));

    }
}