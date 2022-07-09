package com.ji_tarras.workouthome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Guidebooks extends AppCompatActivity {
    TextView workouts, diets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidebooks);
        Objects.requireNonNull(getSupportActionBar()).hide();

        workouts = findViewById(R.id.gWorkouts);
        diets = findViewById(R.id.gDiets);

        workouts.setOnClickListener(v -> {
            Intent workoutList = new Intent(Guidebooks.this, WorkoutList.class);
            startActivity(workoutList);
        });

        diets.setOnClickListener(v -> {
            Intent dietList = new Intent(Guidebooks.this, RecipeList.class);
            startActivity(dietList);
        });
    }

}