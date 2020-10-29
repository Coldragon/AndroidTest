package com.example.quiztest.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.quiztest.R;

public class MainActivity extends AppCompatActivity {

	private Button start_game;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		start_game = findViewById(R.id.start_button);
		start_game.setOnClickListener((v)->{
			Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
			startActivity(gameActivity);
		});

	}
}