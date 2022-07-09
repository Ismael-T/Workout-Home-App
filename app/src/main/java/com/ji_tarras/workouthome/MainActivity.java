package com.ji_tarras.workouthome;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.ji_tarras.workouthome.databinding.ActivityMainBinding;

import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final Handler mHandler = new Handler();
    private ActivityMainBinding binding;
    private MaterialTimePicker picker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private boolean alarmFlag;

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
        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show();

        mHandler.postDelayed(mRunnable, 2000);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        createNotificationChannel();

        binding.linearLayoutWOH.setOnClickListener(v -> {
            Intent woh = new Intent(MainActivity.this, WorkoutSets.class);
            startActivity(woh);
        });

        binding.linearLayoutWPM.setOnClickListener(v -> {
            Intent wpm = new Intent(MainActivity.this, WeeklyProgress.class);
            startActivity(wpm);
        });

        binding.GuideBtn.setOnClickListener(v -> {
            Intent g = new Intent(MainActivity.this, Guidebooks.class);
            startActivity(g);
        });

        SharedPreferences sp = getSharedPreferences("time_sp", Context.MODE_PRIVATE);
        String time = sp.getString("time", "");
        if (time.length() != 1) {
            binding.selectedTimeTv.setText(time);
            binding.alarmStatusTv.setText("Daily Workout Alarm currently set to " + time);
        }

        binding.selectedTimeTv.setOnClickListener(v -> showTimePicker());

        binding.setAlarmBtn.setOnClickListener(v -> setAlarm());

        binding.cancelAlarmBtn.setOnClickListener(v -> cancelAlarm());

    }

    @SuppressLint({"InlinedApi", "SetTextI18n"})
    private void cancelAlarm() {
        Intent intent = new Intent(this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        if (alarmManager == null) {

            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        }

        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Daily Workout Alarm cancelled", Toast.LENGTH_SHORT).show();
        binding.alarmStatusTv.setText("Daily Workout Alarm is currently not set.");
        SharedPreferences sp = getSharedPreferences("time_sp", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("time", "0");
        editor.apply();
        alarmFlag = false;
    }

    @SuppressLint({"UnspecifiedImmutableFlag", "SetTextI18n"})
    private void setAlarm() {
        if (!alarmFlag) {
            Toast.makeText(getApplicationContext(), "Please set time for the alarm.", Toast.LENGTH_LONG).show();
            return;
        }

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);

        SharedPreferences sp = getSharedPreferences("time_sp", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("time", binding.selectedTimeTv.getText().toString());
        editor.apply();

        binding.alarmStatusTv.setText("Daily Workout Alarm currently set to " + binding.selectedTimeTv.getText() + "...");

        Toast.makeText(this, "Daily Workout Alarm set successfully", Toast.LENGTH_SHORT).show();

    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void showTimePicker() {
        alarmFlag = true;
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(7)
                .setMinute(0)
                .setTitleText("Set Daily Workout Alarm Time")
                .build();

        picker.show(getSupportFragmentManager(), "workouthome");

        picker.addOnPositiveButtonClickListener(v -> {

            if (picker.getHour() >= 12) {
                if (picker.getHour() == 12) {
                    if (picker.getMinute() == 0)
                        binding.selectedTimeTv.setText("12 : 00 NN");
                    else
                        binding.selectedTimeTv.setText("12 : " + String.format("%02d", picker.getMinute()) + " PM");
                } else
                    binding.selectedTimeTv.setText(
                            picker.getHour() - 12 + " : " + String.format("%02d", picker.getMinute()) + " PM"
                    );
            } else if (picker.getHour() == 0)
                binding.selectedTimeTv.setText("12 : " + String.format("%02d", picker.getMinute()) + " AM");
            else
                binding.selectedTimeTv.setText(picker.getHour() + " : " + String.format("%02d", picker.getMinute()) + " AM");


            calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, picker.getHour());
            calendar.set(Calendar.MINUTE, picker.getMinute());
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

        });

    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "workouthomeReminderChannel";
            String description = "Channel For Workout Home";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("workouthome", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }


    }

}