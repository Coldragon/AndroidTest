package com.example.quiztest.model;

import java.util.List;
import java.util.Random;

public class QuestionBank {
	private List<Question> mQuestionList;
	private int mNextQuestionIndex;

	public QuestionBank(List<Question> questionList) {
		mQuestionList = questionList;
		// Shuffle not avaiable
		int n = mQuestionList.size();
		Random random = new Random();
		random.nextInt();
		for (int i = 0; i < n; i++) {
			int change = i + random.nextInt(n - i);
			Question helper = mQuestionList.get(i);
			mQuestionList.set(i, mQuestionList.get(change));
			mQuestionList.set(change, helper);
		}
		mNextQuestionIndex=0;
	}

	public Question getQuestion()
	{
		int returnedIndex = mNextQuestionIndex;
		mNextQuestionIndex++;

		if (mNextQuestionIndex>=mQuestionList.size())
			mNextQuestionIndex=0;

		return mQuestionList.get(returnedIndex);
	}
}
