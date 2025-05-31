package com.example.laba7maks;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ClassicModeActivity extends AppCompatActivity {
    private GameLogic game;
    private TextView tvHint;
    private EditText etGuess;
    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_mode);

        game = new GameLogic();
        tvHint = findViewById(R.id.tvHint);
        etGuess = findViewById(R.id.etGuess);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnReset = findViewById(R.id.btnReset);

        tvHint.setText("Угадайте число от 1 до 100");
        updateAttemptsDisplay();
        btnSubmit.setOnClickListener(v -> {
            try {
                int guess = Integer.parseInt(etGuess.getText().toString());
                if (guess < 1 || guess > 100) {
                    tvHint.setText("Число должно быть от 1 до 100");
                    updateAttemptsDisplay();
                    return;
                }

                result = game.checkGuess(guess);
                tvHint.setText(result);
                if (game.isGameOver()){
                    game.resetGameClassic();
                }
                updateAttemptsDisplay();

                etGuess.setText("");
            } catch (NumberFormatException e) {
                tvHint.setText("Введите число от 1 до 100");
                updateAttemptsDisplay();
            }
        });

        btnBack.setOnClickListener(v -> finish());

        btnReset.setOnClickListener(v -> {
            game.resetGameClassic();
            tvHint.setText("Угадайте число от 1 до 100");
            updateAttemptsDisplay();
            etGuess.setText("");
        });
    }

    private void updateAttemptsDisplay() {
        if (!game.isGameOver()) {
            tvHint.append("\nОсталось попыток: " + game.getRemainingAttempts());
        }
    }
}