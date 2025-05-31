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

        btnClassic.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ClassicModeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        btnPlayerNumber.setOnClickListener(v -> {
            try {
                startActivity(new Intent(this, PlayerNumberModeActivity.class));
            } catch (Exception e) {
                Log.e("MainActivity", "Ошибка запуска PlayerNumberModeActivity", e);
            }
        });
    }
}