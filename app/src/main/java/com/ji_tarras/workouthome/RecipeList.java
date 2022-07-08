package com.ji_tarras.workouthome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeList extends AppCompatActivity {
    TextView sweetPotato, summerSkillet, mushroom;
    Integer inf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        getSupportActionBar().hide();
        sweetPotato = findViewById(R.id.sweetPotato);
        summerSkillet = findViewById(R.id.summerSkillet);
        mushroom = findViewById(R.id.mushroom);

        sweetPotato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inf = 0;
                Intent i = new Intent(RecipeList.this, RecipeInfo.class);
                i.putExtra("recipe", inf);
                startActivity(i);
            }
        });
        summerSkillet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inf = 1;
                Intent i = new Intent(RecipeList.this, RecipeInfo.class);
                i.putExtra("recipe", inf);
                startActivity(i);
            }
        });
        mushroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inf = 2;
                Intent i = new Intent(RecipeList.this, RecipeInfo.class);
                i.putExtra("recipe", inf);
                startActivity(i);
            }
        });
    }
}
