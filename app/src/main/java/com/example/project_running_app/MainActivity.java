package com.example.project_running_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView stepsTextView, timerTextView;
    private Button startButton, stopButton, resetButton, showRunButton;
    private int stepsCount = 0;
    private long secondsCount = 0;

    private boolean isRunning = false;
    private Handler handler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stepsTextView = findViewById(R.id.stepsTextView);
        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        resetButton = findViewById(R.id.resetButton);
        showRunButton = findViewById(R.id.showRunButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRun();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRun();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetRun();
            }
        });

        showRunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    showRunDetails();
                }
            }
        });
    }

    private void startRun() {
        isRunning = true;
        handler.postDelayed(timerRunnable, 1000);
    }

    private void stopRun() {
        isRunning = false;
        handler.removeCallbacks(timerRunnable);
        showRunButton.setEnabled(true);
    }

    private void resetRun() {
        stepsCount = 0;
        secondsCount = 0;
        updateDisplay();
    }

    private void showRunDetails() {
        Intent intent = new Intent(MainActivity.this, RunDetailsActivity.class);
        intent.putExtra("stepsCount", stepsCount);
        intent.putExtra("secondsCount", (long) secondsCount);
        startActivity(intent);
    }

    private Runnable timerRunnable = new Runnable() {
        private static final int stepsPerSecond = 3;
        @Override
        public void run() {
            secondsCount++;
            stepsCount+= stepsPerSecond; // Increment steps
            updateDisplay();
            handler.postDelayed(this, 1000 / stepsPerSecond);

            Log.d("MainActivity", "Seconds: " + secondsCount);
        }
    };

    private void updateDisplay() {
        StringBuilder stepsBuilder = new StringBuilder("Steps: ");
        stepsBuilder.append(stepsCount);

        StringBuilder timeBuilder = new StringBuilder("Time: ");
        timeBuilder.append(secondsCount).append(" seconds");

        stepsTextView.setText(stepsBuilder.toString());
        timerTextView.setText(timeBuilder.toString());
    }

}
