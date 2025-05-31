package com.example.laba7maks;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnClassic = findViewById(R.id.btnClassic);
        Button btnPlayerNumber = findViewById(R.id.btnPlayerNumber);
        Button btnLevelsMode = findViewById(R.id.btnLevelsMode);
        Button btnExit = findViewById(R.id.btnExit);

        btnClassic.setOnClickListener(v -> {
            try {
                startActivity(new Intent(this, ClassicModeActivity.class));
            } catch (Exception e) {
                Log.e("MainActivity", "Ошибка запуска ClassicModeActivity", e);
            }
        });

        btnPlayerNumber.setOnClickListener(v -> {
            try {
                startActivity(new Intent(this, PlayerNumberModeActivity.class));
            } catch (Exception e) {
                Log.e("MainActivity", "Ошибка запуска PlayerNumberModeActivity", e);
            }
        });
        btnLevelsMode.setOnClickListener(v -> {
            try {
                startActivity(new Intent(this, LevelsModeActivity.class));
            } catch (Exception e) {
                Log.e("MainActivity", "Ошибка запуска LevelsModeActivity", e);
            }
        });
        btnExit.setOnClickListener(v -> finish());
    }
}