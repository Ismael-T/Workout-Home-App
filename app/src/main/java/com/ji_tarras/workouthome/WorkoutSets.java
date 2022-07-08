package com.ji_tarras.workouthome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WorkoutSets extends AppCompatActivity implements View.OnClickListener {

    TextView light, moderate, intense;

    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

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
        light = findViewById(R.id.lightWorkout);
        moderate = findViewById(R.id.moderateWorkout);
        intense = findViewById(R.id.intenseWorkout);

    }

    @Override
    public void onClick(View v) {
        int set;
        switch (v.getId()) {
            case R.id.lightWorkout:
                set = 1;
                break;
            case R.id.moderateWorkout:
                set = 2;
                break;
            case R.id.intenseWorkout:
                set = 3;
                break;
        }

        Intent j = new Intent(WorkoutSets.this, WorkoutStart.class);
        startActivity(j);
        finish();

    }
}