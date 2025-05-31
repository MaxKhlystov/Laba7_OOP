package com.example.laba7maks;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ClassicModeActivity extends AppCompatActivity {
    private GameLogic game;
    private TextView tvHint;
    private EditText etGuess;
    private Spinner spinnerAttempts;
    private Button btnStart, btnSubmit, btnReset, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_mode);

        game = new GameLogic();
        tvHint = findViewById(R.id.tvHint);
        etGuess = findViewById(R.id.etGuess);
        etGuess.setVisibility(View.GONE);
        spinnerAttempts = findViewById(R.id.spinnerAttempts);
        btnStart = findViewById(R.id.btnStart);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnReset = findViewById(R.id.btnReset);
        btnBack = findViewById(R.id.btnBack);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAttempts.setAdapter(adapter);
        spinnerAttempts.setSelection(14);

        tvHint.setText("Выберите количество попыток и нажмите 'Старт'");

        // Обработка кнопки "Старт"
        btnStart.setOnClickListener(v -> {
            int maxAttempts = (int) spinnerAttempts.getSelectedItem();
            game.setMaxAttempts(maxAttempts);
            game.resetGameClassic();
            tvHint.setText("Угадайте число от 1 до 100");
            updateAttemptsDisplay();

            // Меняем видимость кнопок
            btnStart.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.VISIBLE);
            etGuess.setVisibility(View.VISIBLE);
            spinnerAttempts.setEnabled(false);
        });

        btnSubmit.setOnClickListener(v -> {
            try {
                int guess = Integer.parseInt(etGuess.getText().toString());
                if (guess < 1 || guess > 100) {
                    tvHint.setText("Число должно быть от 1 до 100");
                    return;
                }

                String result = game.checkGuess(guess);
                tvHint.setText(result);
                if (game.isGameOver()) {
                    btnSubmit.setVisibility(View.GONE);
                    etGuess.setVisibility(View.GONE);
                    btnStart.setVisibility(View.VISIBLE);
                    spinnerAttempts.setEnabled(true);
                }
                updateAttemptsDisplay();
                etGuess.setText("");
            } catch (NumberFormatException e) {
                tvHint.setText("Введите число от 1 до 100");
            }
        });

        btnBack.setOnClickListener(v -> finish());

        btnReset.setOnClickListener(v -> {
            game.resetGameClassic();
            tvHint.setText("Выберите количество попыток и нажмите 'Старт'");
            btnStart.setVisibility(View.VISIBLE);
            btnSubmit.setVisibility(View.GONE);
            spinnerAttempts.setEnabled(true);
            etGuess.setText("");
        });
    }

    private void updateAttemptsDisplay() {
        if (!game.isGameOver()) {
            tvHint.append("\nОсталось попыток: " + game.getRemainingAttempts());
        }
    }
}