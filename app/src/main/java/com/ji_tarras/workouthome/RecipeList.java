package com.ji_tarras.workouthome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class RecipeList extends AppCompatActivity {
    TextView sweetPotato, summerSkillet, mushroom;
    Integer inf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        Objects.requireNonNull(getSupportActionBar()).hide();
        sweetPotato = findViewById(R.id.sweetPotato);
        summerSkillet = findViewById(R.id.summerSkillet);
        mushroom = findViewById(R.id.mushroom);

        sweetPotato.setOnClickListener(v -> {
            inf = 0;
            Intent i = new Intent(RecipeList.this, RecipeInfo.class);
            i.putExtra("recipe", inf);
            startActivity(i);
        });
        summerSkillet.setOnClickListener(v -> {
            inf = 1;
            Intent i = new Intent(RecipeList.this, RecipeInfo.class);
            i.putExtra("recipe", inf);
            startActivity(i);
        });
        mushroom.setOnClickListener(v -> {
            inf = 2;
            Intent i = new Intent(RecipeList.this, RecipeInfo.class);
            i.putExtra("recipe", inf);
            startActivity(i);
        });
    }
}
