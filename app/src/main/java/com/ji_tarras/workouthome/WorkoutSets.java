package com.ji_tarras.workouthome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class WorkoutSets extends AppCompatActivity {

    TextView light, moderate, intense;

    private boolean doubleBackToExitPressedOnce;
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };
    private final Handler mHandler = new Handler();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press BACK again to cancel workout", Toast.LENGTH_SHORT).show();

        mHandler.postDelayed(mRunnable, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_sets);
        Objects.requireNonNull(getSupportActionBar()).hide();
        light = findViewById(R.id.lightWorkout);
        moderate = findViewById(R.id.moderateWorkout);
        intense = findViewById(R.id.intenseWorkout);

        light.setOnClickListener(v -> {
            Intent ligW = new Intent(WorkoutSets.this, WorkoutStart.class);
            ligW.putExtra("wos", 1);
            startActivity(ligW);
        });
        moderate.setOnClickListener(v -> {
            Intent modW = new Intent(WorkoutSets.this, WorkoutStart.class);
            modW.putExtra("wos", 2);
            startActivity(modW);
        });
        intense.setOnClickListener(v -> {
            Intent intW = new Intent(WorkoutSets.this, WorkoutStart.class);
            intW.putExtra("wos", 3);
            startActivity(intW);
        });
    }
}