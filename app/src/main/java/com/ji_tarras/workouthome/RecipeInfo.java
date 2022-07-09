package com.ji_tarras.workouthome;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class RecipeInfo extends AppCompatActivity {
    String[] Dish = {"Sweet Potato and Broccoli with Eggs", "Summer Skillet Vegetable Egg Scramble", "Spinach, Mushroom and Egg Casserole"};
    Integer[] Img = {R.drawable.sweetpotato, R.drawable.summerskillet, R.drawable.mushroom};

    ImageView rImg;
    TextView rName, rIng, rSteps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info);
        Objects.requireNonNull(getSupportActionBar()).hide();
        rImg = findViewById(R.id.rImg);
        rName = findViewById(R.id.rName);
        rIng = findViewById(R.id.rIng);
        rSteps = findViewById(R.id.rSteps);
        setContent(getIntent().getIntExtra("recipe", 0));
    }

    public void setContent(Integer r) {

        rImg.setImageResource(Img[r]);
        rName.setText(Dish[r]);
        switch (r) {
            case 0:
                rIng.setText(R.string.sweetpotato_ing);
                rSteps.setText(R.string.sweetpotato_steps);
                break;
            case 1:
                rIng.setText(R.string.summer_ing);
                rSteps.setText(R.string.summer_steps);
                break;
            case 2:
                rIng.setText(R.string.mushroom_ing);
                rSteps.setText(R.string.mushroom_steps);
                break;
        }
    }
}