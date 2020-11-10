package com.example.quiztest.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiztest.R;
import com.example.quiztest.model.User;

public class MainActivity extends AppCompatActivity {

	private TextView mTextViewHighScore;
	private static final int GAME_ACTIVITY_REQUEST_CODE = 42;
	private static final int USERNAME_ACTIVITY_REQUEST_CODE = 43;
	private final User mUser = new User();
	private TextView mTextViewUsername;
	private SharedPreferences mSharedPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getSupportActionBar().hide();
		setContentView(R.layout.activity_main);

		mTextViewHighScore = findViewById(R.id.highscore_value);
		Button buttonStartGame = findViewById(R.id.start_button);
		mTextViewUsername = findViewById(R.id.username_main_activity);
		mSharedPreferences = getSharedPreferences(getString(R.string.shared_preference_filename), MODE_PRIVATE);

		mUser.setFirstName(mSharedPreferences.getString("firstname", null));
		if(mUser.getFirstName() == null)
		{
			Intent usernameActivity = new Intent(MainActivity.this, UsernameActivity.class);
			startActivityForResult(usernameActivity, USERNAME_ACTIVITY_REQUEST_CODE);
		}
		else
		{
			updateUsernameView();
			updateScoreView(mSharedPreferences.getInt("highscore", 0));
		}

		buttonStartGame.setOnClickListener((v)->{
			Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
			startActivityForResult(gameActivity, GAME_ACTIVITY_REQUEST_CODE);
		});
	}

	private void updateUsernameView() {
		mTextViewUsername.setText(mUser.getFirstName());
	}

	private void updateScoreView(int score) {
		mTextViewHighScore.setText(""+ score);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
			int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
			int highscore = mSharedPreferences.getInt(getString(R.string.preference_key_highscore), 0);
			if(score > highscore)
			{
				mSharedPreferences.edit().putInt(getString(R.string.preference_key_highscore), score).apply();
				updateScoreView(score);
			}
		}

		if (USERNAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
			mUser.setFirstName(mSharedPreferences.getString(getString(R.string.preference_key_username), null));
			updateUsernameView();
		}
	}
}