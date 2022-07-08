package com.ji_tarras.workouthome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;

public class WorkoutList extends AppCompatActivity {
    String[] workouts = {"Jump Squat", "Bent Over Twist", "Alternating Side Lunge", "Bicycle Crunch", "Burpees", "Push Up", "Plank Up And Down", "Side Plank", "Pistol Squat"};
    Integer[] gifs = {R.drawable.j_squat, R.drawable.bent_over_twist, R.drawable.alt_side_lunge, R.drawable.bike_crunch, R.drawable.burpees, R.drawable.push_up, R.drawable.plank_updown, R.drawable.side_plank, R.drawable.p_squat};
    ListView workoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Workout Wiki");
        setContentView(R.layout.activity_workout_list);

        workoutList = findViewById(R.id.workoutList);
        CharacterListView WorkoutAdapter = new CharacterListView();
        workoutList.setAdapter(WorkoutAdapter);

        workoutList.setOnItemClickListener((parent, view, i, id) -> {
            Intent intent = new Intent(getApplicationContext(), WorkoutInfo.class);
            intent.putExtra("Workouts", workouts[i]);
            intent.putExtra("GIFs", gifs[i]);
            startActivity(intent);
        });
    }

    class CharacterListView extends BaseAdapter {

        @Override
        public int getCount() {
            return workouts.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint({"ViewHolder", "InflateParams"})
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.c_list_layout, null);
            GifImageView img = view.findViewById(R.id.workoutGif);
            TextView character = view.findViewById(R.id.workoutTv);
            img.setImageResource(gifs[i]);
            character.setText(workouts[i]);
            return view;
        }
    }
}