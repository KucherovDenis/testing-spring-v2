package ru.denku.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {

    private List<Question> questions;

    private int numberOfErrors;

    public Test(int numberOfErrors) {
        questions = new ArrayList<>();
        this.numberOfErrors = numberOfErrors;
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public int getNumberOfErrors() {
        return numberOfErrors;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }
}
