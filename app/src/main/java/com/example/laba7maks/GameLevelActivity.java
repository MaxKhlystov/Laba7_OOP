package com.example.laba7maks;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameLevelActivity extends AppCompatActivity {

    private int level, maxNumber, maxAttempts;
    private int targetNumber, attemptsLeft;
    private TextView tvStatus;
    private EditText etInput;
    private Button btnSubmit, btnAgain, btnBackToLevel;
    private boolean isGameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level);

        // Получаем уровень из интента
        level = getIntent().getIntExtra("level", 1);

        // Инициализируем элементы интерфейса
        tvStatus = findViewById(R.id.tvStatus);
        etInput = findViewById(R.id.etInput);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnAgain = findViewById(R.id.btnAgain);
        btnBackToLevel = findViewById(R.id.btnBackToLevel);

        setupLevel(level);
        generateNumber();

        btnSubmit.setOnClickListener(v -> checkGuess());

        btnBackToLevel.setOnClickListener(v -> {
            finish(); // Возврат к списку уровней
        });

        btnAgain.setOnClickListener(v -> {
            generateNumber();  // Загадать новое число
            attemptsLeft = maxAttempts;  // Сбросить попытки
            btnSubmit.setEnabled(true);  // Включить кнопку проверки
            etInput.setText("");  // Очистить поле ввода
            tvStatus.setText("Уровень " + level + ": угадайте число от 1 до " + maxNumber);
            btnSubmit.setVisibility(View.VISIBLE);
            btnAgain.setVisibility(View.GONE);
            btnBackToLevel.setVisibility(View.GONE);
            isGameOver = false;
        });
    }

    private void setupLevel(int level) {
        switch (level) {
            case 1: maxNumber = 20; maxAttempts = 5; break;
            case 2: maxNumber = 40; maxAttempts = 8; break;
            case 3: maxNumber = 60; maxAttempts = 11; break;
            case 4: maxNumber = 80; maxAttempts = 15; break;
            case 5: maxNumber = 100; maxAttempts = 10; break;
            default: maxNumber = 100; maxAttempts = 10;
        }
        attemptsLeft = maxAttempts;
        tvStatus.setText("Уровень " + level + ": угадайте число от 1 до " + maxNumber);
    }

    private void generateNumber() {
        targetNumber = new Random().nextInt(maxNumber) + 1;
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(etInput.getText().toString());

            if (guess < 1 || guess > maxNumber) {
                tvStatus.setText("Введите число от 1 до " + maxNumber);
                return;
            }

            attemptsLeft--;

            if (guess == targetNumber) {
                tvStatus.setText("Поздравляем! Вы прошли уровень " + level + "\nЗагаданное число: " + targetNumber);
                btnSubmit.setVisibility(View.GONE);
                btnAgain.setVisibility(View.GONE);
                btnBackToLevel.setVisibility(View.VISIBLE);
                isGameOver = true;

                // Сохраняем прогресс
                SharedPreferences prefs = getSharedPreferences("level_progress", MODE_PRIVATE);
                int unlockedLevel = prefs.getInt("unlocked_level", 1);
                if (level == unlockedLevel && level < 5) {
                    prefs.edit().putInt("unlocked_level", level + 1).apply();
                }

            } else if (attemptsLeft > 0) {
                tvStatus.setText((guess < targetNumber ? "Загаданное число больше" : "Загаданное число меньше")
                        + "\nОсталось попыток: " + attemptsLeft);
            } else {
                tvStatus.setText("Вы проиграли! Загаданное число было: " + targetNumber);
                btnSubmit.setVisibility(View.GONE);
                btnAgain.setVisibility(View.VISIBLE);
                btnBackToLevel.setVisibility(View.VISIBLE);
                isGameOver = true;
            }

        } catch (NumberFormatException e) {
            tvStatus.setText("Введите допустимое число");
        }
    }
}
