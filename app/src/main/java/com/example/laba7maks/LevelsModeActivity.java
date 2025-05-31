package com.example.laba7maks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.laba7maks.GameLevelActivity;

public class LevelsModeActivity extends AppCompatActivity {

    private Button[] levelButtons = new Button[5];
    private Button btnBackToMain;
    private SharedPreferences prefs;
    int unlockedLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_mode);

        prefs = getSharedPreferences("level_progress", MODE_PRIVATE);
        unlockedLevel = prefs.getInt("unlocked_level", 1);

        levelButtons[0] = findViewById(R.id.btnLevel1);
        levelButtons[1] = findViewById(R.id.btnLevel2);
        levelButtons[2] = findViewById(R.id.btnLevel3);
        levelButtons[3] = findViewById(R.id.btnLevel4);
        levelButtons[4] = findViewById(R.id.btnLevel5);
        btnBackToMain = findViewById(R.id.btnBackToMain);

        setEnableBtn();

        btnBackToMain.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Обновляем unlockedLevel из SharedPreferences
        unlockedLevel = prefs.getInt("unlocked_level", 1);
        // Обновляем состояние кнопок
        setEnableBtn();
    }

    private void startLevel(int level) {
        Intent intent = new Intent(this, GameLevelActivity.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }

    private void setEnableBtn(){
        for (int i = 0; i < levelButtons.length; i++) {
            int level = i + 1;
            Button btn = levelButtons[i];
            btn.setEnabled(level <= unlockedLevel);
            btn.setAlpha(level <= unlockedLevel ? 1f : 0.5f);
            btn.setOnClickListener(v -> startLevel(level));
        }
    }
}

