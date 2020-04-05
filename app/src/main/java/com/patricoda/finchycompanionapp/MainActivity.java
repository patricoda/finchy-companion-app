package com.patricoda.finchycompanionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.patricoda.finchycompanionapp.sound.SoundManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SoundManager soundManager = new SoundManager(this.getApplicationContext());

        ImageButton button = findViewById(R.id.finchy);
        button.setOnClickListener(
            v -> {
               soundManager.playSound();
            }
        );
    }
}
