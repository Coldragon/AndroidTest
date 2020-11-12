package com.example.quiztest.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quiztest.BuildConfig;
import com.example.quiztest.R;

public class UsernameActivity extends AppCompatActivity {

	Button mButtonValidUsername;
	EditText mEditTextUsername;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_username);

		mButtonValidUsername = findViewById(R.id.valid_username);
		mEditTextUsername = findViewById(R.id.username_field);
		mButtonValidUsername.setOnClickListener((v)->{
			String input = mEditTextUsername.getText().toString();
			if(!input.isEmpty())
			{
				SharedPreferences preferences = getSharedPreferences(getString(R.string.shared_preference_filename), MODE_PRIVATE);
				preferences.edit().putString(getString(R.string.preference_key_username), input).apply();
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
				if(BuildConfig.DEBUG) 	Log.d("UsernameActivity", "onCreate: test");
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Username can't be empty",Toast.LENGTH_SHORT).show();
			}
		});

	}
}