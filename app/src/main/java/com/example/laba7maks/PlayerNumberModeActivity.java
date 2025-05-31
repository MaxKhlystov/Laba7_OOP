package com.example.laba7maks;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlayerNumberModeActivity extends AppCompatActivity {
    private int playerNumber;
    private GameLogic game = new GameLogic();;
    private int attempts;
    private boolean numberSet;
    private TextView tvStatus;
    private TextView tvNumComp;
    private EditText etInput;
    private Button btnHigher;
    private Button btnLower;
    private Button btnStart;
    private boolean high;
    private int compNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_number);

        tvStatus = findViewById(R.id.tvStatus);
        tvNumComp = findViewById(R.id.tvNumComp);
        tvNumComp.setVisibility(View.GONE);
        etInput = findViewById(R.id.etInput);
        etInput.setVisibility(View.VISIBLE);
        btnStart = findViewById(R.id.btnStart);
        btnStart.setVisibility(View.GONE);
        btnHigher = findViewById(R.id.btnHigher);
        btnLower = findViewById(R.id.btnLower);

        Button btnAction = findViewById(R.id.btnAction);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnReset = findViewById(R.id.btnReset);

        btnHigher.setVisibility(View.GONE);
        btnLower.setVisibility(View.GONE);

        btnAction.setOnClickListener(v -> {
            if (!numberSet) {
                try {
                    String input = etInput.getText().toString();
                    if (input.isEmpty()) {
                        tvStatus.setText("Введите число!");
                        return;
                    }
                    playerNumber = Integer.parseInt(input);
                    if (playerNumber < 1 || playerNumber > 100) {
                        tvStatus.setText("Число должно быть от 1 до 100");
                        return;
                    }
                    numberSet = true;
                    game.setSecretNumber(playerNumber);
                    tvStatus.setText("Компьютер угадывает число. \nВаше число: " + playerNumber);
                    btnStart.setVisibility(View.VISIBLE);
                    etInput.setVisibility(View.GONE);
                    tvNumComp.setVisibility(View.VISIBLE);
                    btnAction.setVisibility(View.GONE);  // Скрываем кнопку (но потом она должна вернуться!)
                    btnHigher.setVisibility(View.VISIBLE);
                    btnLower.setVisibility(View.VISIBLE);
                } catch (NumberFormatException e) {
                    tvStatus.setText("Введите корректное число!");
                }
            }
        });
        btnStart.setOnClickListener(v->{
            compNumber = (int) (Math.random() * 100) + 1;
            tvNumComp.setText(String.valueOf(compNumber));
            btnStart.setVisibility(View.GONE);
        });
        btnHigher.setOnClickListener(v -> {
            high = true;
            compNumber = game.checkNumPlayer(compNumber, high);
            tvNumComp.setText(String.valueOf(compNumber));
            if (compNumber == playerNumber) {
                tvStatus.setText("Компьютер угадал ваше число!");
            } else if (game.isGameOver()) {
                tvStatus.setText("Компьютер не угадал число");
            }
        });

        btnLower.setOnClickListener(v -> {
            high = false;
            compNumber = game.checkNumPlayer(compNumber, high);
            tvNumComp.setText(String.valueOf(compNumber));
            if (compNumber == playerNumber) {
                tvStatus.setText("Компьютер угадал ваше число!");
            } else if (game.isGameOver()) {
                tvStatus.setText("Компьютер не угадал число");
            }
        });
        btnBack.setOnClickListener(v -> finish());

        btnReset.setOnClickListener(v -> {
            numberSet = false;
            attempts = 0;
            game.resetGamePlayerNum();
            tvStatus.setText("Загадайте число от 1 до 100");
            etInput.setHint("Ваше число");
            etInput.setText("");
            btnAction.setVisibility(View.VISIBLE);  // <-- Важно!
            etInput.setVisibility(View.VISIBLE);
            tvNumComp.setVisibility(View.GONE);
            btnHigher.setVisibility(View.GONE);
            btnLower.setVisibility(View.GONE);
            btnStart.setVisibility(View.GONE);  // <-- Добавьте, если её нет
        });
    }
}