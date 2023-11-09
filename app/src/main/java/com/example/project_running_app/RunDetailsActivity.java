package com.example.project_running_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RunDetailsActivity extends AppCompatActivity {

    private TextView dateTextView, metersTextView, caloriesTextView, timeTextView;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_details);

        dateTextView = findViewById(R.id.dateTextView);
        metersTextView = findViewById(R.id.metersTextView);
        caloriesTextView = findViewById(R.id.caloriesTextView);
        timeTextView = findViewById(R.id.timeTextView);
        returnButton = findViewById(R.id.returnButton);

        //  data from the previous activity
        int stepsCount = getIntent().getIntExtra("stepsCount", 0);
        long secondsCount = (int) getIntent().getLongExtra("secondsCount", 0);

        Log.d("RunDetailsActivity", "Steps Count: " + stepsCount);
        Log.d("RunDetailsActivity", "Seconds Count: " + secondsCount);

        // Calculate run details
        double meters = stepsCount * 0.8;
        double calories = stepsCount * 0.04;

        // Display run details
        dateTextView.setText("Date: " + getCurrentDate());
        metersTextView.setText("Meters: " + String.format(Locale.getDefault(), "%.2f", meters));
        caloriesTextView.setText("Calories: " + String.format(Locale.getDefault(), "%.2f", calories));
        timeTextView.setText("Time: " + secondsCount + " seconds");

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity and return to the previous one
            }
        });
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(new Date());
    }
}
