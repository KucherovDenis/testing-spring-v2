package ru.denku.domain;

import ru.denku.entity.Question;
import ru.denku.entity.Test;
import ru.denku.entity.TestResult;

import java.util.ArrayList;
import java.util.List;

public class TestAnalyzer implements Analyzer {
    @Override
    public TestResult analyze(Test test) {
        int correctAnswer = 0;
        List<Boolean> answers = new ArrayList<>();
        for (Question question : test.getQuestions()) {
            if (question.getUserAnswer().equals(question.getCorrectAnswer())) {
                correctAnswer++;
                answers.add(true);
            } else {
                answers.add(false);
            }
        }
        return new TestResult(test.getQuestions().size(), test.getNumberOfErrors(), correctAnswer, answers);
    }
}
