package com.ji_tarras.workouthome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class WorkoutStart extends AppCompatActivity {
    private final Handler mHandler = new Handler();
    int flag, exercise = 0, tw, lw, mw, iw;
    TextView wstv, wstv2, wttv, wctv;
    GifImageView wsGIF;
    CardView cardView;
    CountDownTimer light_cdt, mod_cdt, intense_cdt, moderate_rest, intensive_rest, message;
    String[] light = {"Jump Squat", "Bent Over Twist", "Alternating Side Lunge", "Jump Squat", "Bicycle Crunch", "Burpees"};
    Integer[] lightGifs = {R.drawable.j_squat, R.drawable.bent_over_twist, R.drawable.alt_side_lunge, R.drawable.j_squat, R.drawable.bike_crunch, R.drawable.burpees};
    String[] moderate = {"Jump Squat", "Bent Over Twist", "Alternating Side Lunge", "Bicycle Crunch", "Burpees", "Alternating Side Lunge", "Push Up", "Plank Up And Down"};
    Integer[] moderateGifs = {R.drawable.j_squat, R.drawable.bent_over_twist, R.drawable.alt_side_lunge, R.drawable.bike_crunch, R.drawable.burpees, R.drawable.alt_side_lunge, R.drawable.push_up, R.drawable.plank_updown};
    String[] intense = {"Jump Squat", "Bent Over Twist", "Alternating Side Lunge", "Bicycle Crunch", "Jump Squat", "Burpees", "Push Up", "Plank Up And Down", "Side Plank (Right)", "Side Plank (Left)", "Alternating Side Lunge", "Pistol Squat"};
    Integer[] intenseGifs = {R.drawable.j_squat, R.drawable.bent_over_twist, R.drawable.alt_side_lunge, R.drawable.bike_crunch, R.drawable.j_squat, R.drawable.burpees, R.drawable.push_up, R.drawable.plank_updown, R.drawable.side_plank, R.drawable.side_plank, R.drawable.alt_side_lunge, R.drawable.p_squat};
    String[] msg1 = {"Congratulations", "Great work", "Awesome", "Amazing"};
    String[] msg2 = {"You did it!  ", "Gains acquired!  ", "You leveled up!  ", "Keep it up!  "};
    Random MESSAGE = new Random();
    int MSG1 = MESSAGE.nextInt(4);
    int MSG2 = MESSAGE.nextInt(4);
    private boolean doubleBackToExitPressedOnce;
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
        Toast.makeText(this, "Press BACK again to change workout set", Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(mRunnable, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_start);
        Objects.requireNonNull(getSupportActionBar()).hide();

        SharedPreferences sp = getSharedPreferences("progress_sp", Context.MODE_PRIVATE);
        tw = sp.getInt("tw",0);
        lw = sp.getInt("lw",0);
        mw = sp.getInt("mw",0);
        iw = sp.getInt("iw", 0);

        wstv = findViewById(R.id.workoutStartTv);
        wstv2 = findViewById(R.id.workoutStartTv2);
        wttv = findViewById(R.id.workoutTimerTv);
        cardView = findViewById(R.id.cardView);
        wsGIF = findViewById(R.id.wsGIF);
        wctv = findViewById(R.id.workoutCompletionTv);
        flag = getIntent().getIntExtra("wos", 0);

        invisible();

        switch (flag) {
            case 1:
                light_cdt = new CountDownTimer(5000, 1000) {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTick(long millisUntilFinished) {
                        wttv.setText(String.format(Locale.getDefault(), "%ds", millisUntilFinished / 1000));
                        wctv.setText(exercise + "/" + light.length + " exercises completed");
                    }

                    @Override
                    public void onFinish() {
                        visible();
                        light_timer();
                    }

                }.start();

                break;
            case 2:
                mod_cdt = new CountDownTimer(5000, 1000) {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTick(long millisUntilFinished) {
                        wttv.setText(String.format(Locale.getDefault(), "%ds", millisUntilFinished / 1000));
                        wctv.setText(exercise + "/" + moderate.length + " exercises completed");
                    }

                    @Override
                    public void onFinish() {
                        mod_timer();
                    }

                }.start();

                break;
            case 3:
                intense_cdt = new CountDownTimer(5000, 1000) {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTick(long millisUntilFinished) {
                        wttv.setText(String.format(Locale.getDefault(), "%ds", millisUntilFinished / 1000));
                        wctv.setText(exercise + "/" + intense.length + " exercises completed");
                    }

                    @Override
                    public void onFinish() {
                        intense_timer();
                    }

                }.start();

                break;
        }

    }

    private void light_timer() {
        wsGIF.setImageResource(lightGifs[exercise]);
        light_cdt = new CountDownTimer(30000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                wstv.setText(light[exercise]);
                wstv2.setText("As many as you can ...");
                wttv.setText(String.format(Locale.getDefault(), "%ds", millisUntilFinished / 1000));
                wctv.setText(exercise + "/" + light.length + " exercises completed");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                exercise++;

                if (exercise == 6) {
                    lw++;
                    tw++;
                    SharedPreferences sp = getSharedPreferences("progress_sp", Context.MODE_PRIVATE);
                    @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("lw", lw);
                    editor.putInt("tw", tw);
                    wctv.setText(exercise + "/" + light.length + " exercises completed");
                    message_timer();
                } else {
                    light_timer();
                }

            }

        }.start();

    }

    private void mod_timer() {
        visible();
        wsGIF.setImageResource(moderateGifs[exercise]);
        mod_cdt = new CountDownTimer(30000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                wstv.setText(moderate[exercise]);
                wstv2.setText("As many as you can ...");
                wttv.setText(String.format(Locale.getDefault(), "%ds", millisUntilFinished / 1000));
                wctv.setText(exercise + "/" + moderate.length + " exercises completed");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                exercise++;

                if (exercise == 8) {
                    mw++;
                    tw++;
                    SharedPreferences sp = getSharedPreferences("progress_sp", Context.MODE_PRIVATE);
                    @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("mw", mw);
                    editor.putInt("tw", tw);
                    wctv.setText(exercise + "/" + moderate.length + " exercises completed");
                    message_timer();
                } else {
                    wctv.setText(exercise + "/" + moderate.length + " exercises completed");
                    mod_rest();
                }

            }

        }.start();

    }

    private void mod_rest() {
        invisible();
        moderate_rest = new CountDownTimer(10000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                wstv.setText("Rest");
                wstv2.setText("workout resumes in...");
                wttv.setText(String.format(Locale.getDefault(), "%ds", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                mod_timer();
            }

        }.start();

    }

    private void intense_timer() {
        visible();
        wsGIF.setImageResource(intenseGifs[exercise]);
        intense_cdt = new CountDownTimer(30000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                wstv.setText(intense[exercise]);
                wstv2.setText("As many as you can ...");
                wttv.setText(String.format(Locale.getDefault(), "%ds", millisUntilFinished / 1000));
                wctv.setText(exercise + "/" + intense.length + " exercises completed");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                exercise++;

                if (exercise == 12) {
                    iw++;
                    tw++;
                    SharedPreferences sp = getSharedPreferences("progress_sp", Context.MODE_PRIVATE);
                    @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("iw", iw);
                    editor.putInt("tw", tw);
                    message_timer();
                } else {
                    wctv.setText(exercise + "/" + intense.length + " exercises completed");
                    intense_rest();
                }

            }

        }.start();

    }

    private void intense_rest() {
        invisible();
        intensive_rest = new CountDownTimer(10000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                wstv.setText("Rest");
                wstv2.setText("workout resumes in...");
                wttv.setText(String.format(Locale.getDefault(), "%ds", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                intense_timer();
            }
        }.start();

    }

    private void message_timer() {
        visible();
        wsGIF.setImageResource(R.drawable.finish);
        message = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                wstv.setText(msg1[MSG1]);
                wstv2.setText(msg2[MSG2]);
                wttv.setText(String.format(Locale.getDefault(), "%ds", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                finish();
            }

        }.start();

    }

    private void visible() {
        cardView.setVisibility(View.VISIBLE);
        wsGIF.setVisibility(View.VISIBLE);
    }

    private void invisible() {
        cardView.setVisibility(View.INVISIBLE);
        wsGIF.setVisibility(View.INVISIBLE);
    }

}