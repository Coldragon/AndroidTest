package com.example.quiztest.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiztest.R;
import com.example.quiztest.model.Question;
import com.example.quiztest.model.QuestionBank;
import com.example.quiztest.model.User;

import java.util.ArrayList;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
	private User mUser;
	private ArrayList<Button> mAnswerButton = new ArrayList<Button>();
	private TextView mTextViewQuestion;
	private TextView mTextViewCurrentLife;
	private String mBaseStringCurrentLife;
	private int mScore;
	private QuestionBank mQuestionBank;
	private Question mCurrentQuestion;
	private int mCurrentLife;
	public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
	private boolean mEnableTouchEvent = true;
	@Override
	public void onClick(View v) {
		if ((int) v.getTag() == mCurrentQuestion.getAnswerIndex())
		{
			Toast.makeText(getApplicationContext(), "Correct",Toast.LENGTH_SHORT).show();
			mScore++;
		}
		else
		{
			Toast.makeText(getApplicationContext(), "False",Toast.LENGTH_SHORT).show();
			mCurrentLife--;
			updateVisibleLife();

		}
		mEnableTouchEvent = false;
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (mCurrentLife <= 0) {
					finishedGame();
				}
				else
					generateNextQuestion();
				mEnableTouchEvent = true;
			}
		}, 1000);

	}

	private void updateVisibleLife() {
		mTextViewCurrentLife.setText(mBaseStringCurrentLife + mCurrentLife);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return mEnableTouchEvent && super.dispatchTouchEvent(ev);
	}

	private void finishedGame()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Game Over")
				.setMessage("Your score is : " + mScore)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent();
						intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
						setResult(RESULT_OK, intent);
						finish();
					}
				})
				.setCancelable(false)
				.create()
				.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getSupportActionBar().hide();

		setContentView(R.layout.activity_game);

		mAnswerButton.add(findViewById(R.id.activity_game_answer1_btn));
		mAnswerButton.add(findViewById(R.id.activity_game_answer2_btn));
		mAnswerButton.add(findViewById(R.id.activity_game_answer3_btn));
		mAnswerButton.add(findViewById(R.id.activity_game_answer4_btn));
		mTextViewQuestion = findViewById(R.id.activity_game_question_text);

		mQuestionBank = this.generateQuestions();
		mScore = 0;

		mTextViewCurrentLife = findViewById(R.id.activity_game_lifecount);
		mBaseStringCurrentLife = mTextViewCurrentLife.getText().toString();
		mCurrentLife = 3;
		updateVisibleLife();
		generateNextQuestion();
	}

	private void generateNextQuestion(){
		mCurrentQuestion = mQuestionBank.getQuestion();
		mTextViewQuestion.setText(mCurrentQuestion.getQuestion());
		int index = 0;
		for (Button button: mAnswerButton ) {
			button.setOnClickListener(this);
			button.setText(mCurrentQuestion.getChoiceList().get(index));
			button.setTag(index++);
		}
	}

	private QuestionBank generateQuestions() {
		return new QuestionBank(Arrays.asList(
				new Question("What is the name of the current french president?",
						Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
						1),
				new Question("How many countries are there in the European Union?",
						Arrays.asList("15", "24", "28", "32"),
						2),
				new Question("Who is the creator of the Android operating system?",
						Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
						0),
				new Question("When did the first man land on the moon?",
						Arrays.asList("1958", "1962", "1967", "1969"),
						3),
				new Question("What is the capital of Romania?",
						Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
						0),
				new Question("Who did the Mona Lisa paint?",
						Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
						1),
				new Question("In which city is the composer Frédéric Chopin buried?",
						Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
						2),
				new Question("What is the country top-level domain of Belgium?",
						Arrays.asList(".bg", ".bm", ".bl", ".be"),
						3),
				new Question("What is the house number of The Simpsons?",
						Arrays.asList("42", "101", "666", "742"),
						3)
		));
	}
}