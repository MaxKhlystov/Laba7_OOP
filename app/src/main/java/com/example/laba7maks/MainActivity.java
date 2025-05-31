package com.example.laba7maks;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnClassic = findViewById(R.id.btnClassic);
        Button btnPlayerNumber = findViewById(R.id.btnPlayerNumber);

        btnClassic.setOnClickListener(v ->
                startActivity(new Intent(this, ClassicModeActivity.class)));

        btnPlayerNumber.setOnClickListener(v ->
                startActivity(new Intent(this, PlayerNumberModeActivity.class)));
    }
}