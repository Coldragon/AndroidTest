package com.example.quiztest.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.quiztest.R;
import com.example.quiztest.model.Question;
import com.example.quiztest.model.QuestionBank;

import java.util.ArrayList;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity {

	private ArrayList<Button> answer_buttons = new ArrayList<Button>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		answer_buttons.add(findViewById(R.id.activity_game_answer1_btn));
		answer_buttons.add(findViewById(R.id.activity_game_answer2_btn));
		answer_buttons.add(findViewById(R.id.activity_game_answer3_btn));
		answer_buttons.add(findViewById(R.id.activity_game_answer4_btn));

	}



	private QuestionBank generateQuestions() {
		Question question1 = new Question("What is the name of the current french president?",
				Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
				1);

		Question question2 = new Question("How many countries are there in the European Union?",
				Arrays.asList("15", "24", "28", "32"),
				2);

		Question question3 = new Question("Who is the creator of the Android operating system?",
				Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
				0);

		Question question4 = new Question("When did the first man land on the moon?",
				Arrays.asList("1958", "1962", "1967", "1969"),
				3);

		Question question5 = new Question("What is the capital of Romania?",
				Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
				0);

		Question question6 = new Question("Who did the Mona Lisa paint?",
				Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
				1);

		Question question7 = new Question("In which city is the composer Frédéric Chopin buried?",
				Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
				2);

		Question question8 = new Question("What is the country top-level domain of Belgium?",
				Arrays.asList(".bg", ".bm", ".bl", ".be"),
				3);

		Question question9 = new Question("What is the house number of The Simpsons?",
				Arrays.asList("42", "101", "666", "742"),
				3);

		return new QuestionBank(Arrays.asList(question1,
				question2,
				question3,
				question4,
				question5,
				question6,
				question7,
				question8,
				question9));
	}
}