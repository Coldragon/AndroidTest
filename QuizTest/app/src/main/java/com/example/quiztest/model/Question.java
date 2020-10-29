package com.example.quiztest.model;

import java.util.List;

public class Question {
	private String mQuestion;
	private List<String> mChoice;
	private int mAnswerIndex;

	public Question(String question, List<String> choiceList, int answerIndex) {
		this.setQuestion(question);
		this.setChoiceList(choiceList);
		this.setAnswerIndex(answerIndex);
	}

	public String getQuestion() {
		return mQuestion;
	}

	public void setQuestion(String question) {
		mQuestion = question;
	}

	public List<String> getChoiceList() {
		return mChoice;
	}

	public void setChoiceList(List<String> choiceList) {
		if (choiceList == null) {
			throw new IllegalArgumentException("Array cannot be null");
		}

		mChoice = choiceList;
	}

	public int getAnswerIndex() {
		return mAnswerIndex;
	}

	public void setAnswerIndex(int answerIndex) {
		mAnswerIndex = answerIndex;
	}
}
