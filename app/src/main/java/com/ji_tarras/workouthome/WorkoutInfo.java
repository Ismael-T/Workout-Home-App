package com.ji_tarras.workouthome;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import pl.droidsonroids.gif.GifImageView;

public class WorkoutInfo extends AppCompatActivity {
    GifImageView woGIF;
    TextView woName, woBene, woInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_info);
        Objects.requireNonNull(getSupportActionBar()).hide();
        woGIF = findViewById(R.id.woGIF);
        woName = findViewById(R.id.woName);
        woBene = findViewById(R.id.woBene);
        woInfo = findViewById(R.id.woInfo);
        setContent(getIntent().getStringExtra("Workouts"), getIntent().getIntExtra("GIFs", 0));
    }

    public void setContent(String wos, Integer gif) {
        woGIF.setImageResource(gif);
        woName.setText(wos);
        switch (wos) {
            case "Jump Squat":
                woInfo.setText(R.string.b_jump_squat);
                woBene.setText(R.string.jump_squat);
                break;
            case "Bent Over Twist":
                woInfo.setText(R.string.b_bent_over_twist);
                woBene.setText(R.string.bent_over_twist);
                break;
            case "Alternating Side Lunge":
                woInfo.setText(R.string.b_alt_side_lunge);
                woBene.setText(R.string.alt_side_lunge);
                break;
            case "Bicycle Crunch":
                woInfo.setText(R.string.b_bicycle_crunches);
                woBene.setText(R.string.bicycle_crunches);
                break;
            case "Burpees":
                woInfo.setText(R.string.b_burpees);
                woBene.setText(R.string.burpees);
                break;
            case "Push Up":
                woInfo.setText(R.string.b_pushup);
                woBene.setText(R.string.pushup);
                break;
            case "Plank Up And Down":
                woInfo.setText(R.string.b_plank);
                woBene.setText(R.string.plank);
                break;
            case "Side Plank":
                woInfo.setText(R.string.b_side_plank);
                woBene.setText(R.string.side_plank);
                break;
            case "Pistol Squat":
                woInfo.setText(R.string.b_pistol_squat);
                woBene.setText(R.string.pistol_squat);
                break;
        }
    }
}

