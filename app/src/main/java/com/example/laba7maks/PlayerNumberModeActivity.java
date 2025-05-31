package com.example.laba7maks;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlayerNumberModeActivity extends AppCompatActivity {
    private int playerNumber;
    private GameLogic game = new GameLogic();
    private int attempts;
    private boolean numberSet;
    private TextView tvStatus;
    private TextView tvNumComp;
    private EditText etInput;
    private Button btnHigher;
    private Button btnLower;
    private Button btnStart;
    private Button btnAction;
    private Button btnBack;
    private Button btnReset;
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
        btnAction = findViewById(R.id.btnAction);
        btnBack = findViewById(R.id.btnBackToLevel);
        btnReset = findViewById(R.id.btnReset);

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
                    tvStatus.setText("Компьютер угадывает число... \nВаше число: " + playerNumber);
                    tvNumComp.setText("Кликайте кнопку 'Начать' и победите компьютер!");

                    btnStart.setVisibility(View.VISIBLE);
                    etInput.setVisibility(View.GONE);
                    tvNumComp.setVisibility(View.VISIBLE);
                    btnAction.setVisibility(View.GONE);

                    // УБРАТЬ: кнопки "Больше"/"Меньше" будут видны только после начала
                    btnHigher.setVisibility(View.GONE);
                    btnLower.setVisibility(View.GONE);
                } catch (NumberFormatException e) {
                    tvStatus.setText("Введите корректное число!");
                }
            }
        });

        btnStart.setOnClickListener(v -> {
            compNumber = (int) (Math.random() * 100) + 1;
            tvNumComp.setText("Компьютер предлагает число: " + String.valueOf(compNumber));
            btnStart.setVisibility(View.GONE);

            // Только теперь показываем "Больше"/"Меньше"
            btnHigher.setVisibility(View.VISIBLE);
            btnLower.setVisibility(View.VISIBLE);
        });


        btnHigher.setOnClickListener(v -> {
            high = true;
            compNumber = game.checkNumPlayer(compNumber, high);
            tvNumComp.setText("Компьютер предлагает число: " + String.valueOf(compNumber));
            if (compNumber == playerNumber) {
                tvStatus.setText("Компьютер угадал ваше число!");
                tvNumComp.setText("Ваше число: " + String.valueOf(playerNumber));
                btnHigher.setVisibility(View.GONE);
                btnLower.setVisibility(View.GONE);
            } else if (game.isGameOver()) {
                tvStatus.setText("Компьютер не угадал число");
                btnHigher.setVisibility(View.GONE);
                btnLower.setVisibility(View.GONE);
            }
        });

        btnLower.setOnClickListener(v -> {
            high = false;
            compNumber = game.checkNumPlayer(compNumber, high);
            tvNumComp.setText("Компьютер предлагает число: " + String.valueOf(compNumber));
            if (compNumber == playerNumber) {
                tvStatus.setText("Компьютер угадал ваше число!");
                tvNumComp.setText("Ваше число: " + String.valueOf(playerNumber));
                btnHigher.setVisibility(View.GONE);
                btnLower.setVisibility(View.GONE);
            } else if (game.isGameOver()) {
                tvStatus.setText("Компьютер не угадал число");
                btnHigher.setVisibility(View.GONE);
                btnLower.setVisibility(View.GONE);
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
            tvNumComp.setText("Кликайте кнопку 'Начать' и победите компьютер!");
            btnAction.setVisibility(View.VISIBLE);
            etInput.setVisibility(View.VISIBLE);
            tvNumComp.setVisibility(View.GONE);
            btnStart.setVisibility(View.GONE);
            btnHigher.setVisibility(View.GONE);
            btnLower.setVisibility(View.GONE);
        });
    }
}