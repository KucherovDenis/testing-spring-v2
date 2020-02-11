package ru.denku.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestResult {
    List<Boolean> answerResult;

    public TestResult(int questionsCount, int numbersOfError, int correctAnswers, List<Boolean> answerResult) {
        this.questionsCount = questionsCount;
        this.numbersOfError = numbersOfError;
        this.correctAnswers = correctAnswers;
        this.answerResult = new ArrayList<>(questionsCount);
        this.answerResult.addAll(answerResult);
    }

    private int questionsCount;

    private int numbersOfError;

    private int correctAnswers;

    public int getQuestionsCount() {
        return questionsCount;
    }

    public int getNumbersOfError() {
        return numbersOfError;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public List<Boolean> getAnswerResult() {
        return Collections.unmodifiableList(answerResult);
    }

    public boolean isBad() {
        return correctAnswers < questionsCount - numbersOfError;
    }
}
