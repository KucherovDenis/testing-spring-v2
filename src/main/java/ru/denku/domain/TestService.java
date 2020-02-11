package ru.denku.domain;

import ru.denku.entity.Question;
import ru.denku.entity.QuestionDTO;
import ru.denku.entity.Test;
import ru.denku.entity.TestResult;

import java.util.Objects;

public class TestService implements Runner {

    private final Creator creator;

    private final View view;

    private final Analyzer analyzer;

    public TestService(Creator creator, View view, Analyzer analyzer) {
        this.creator = creator;
        this.view = Objects.requireNonNull(view);
        this.analyzer = Objects.requireNonNull(analyzer);
        creator.prepare();
    }

    @Override
    public void run() {
        TestResult result;
        do {
            Test test = creator.get();
            int index = 1;
            for (Question question : test.getQuestions()) {
                QuestionDTO questionDTO = QuestionMapper.toDTO(question);
                String userAnswer = view.showQuestion(index, questionDTO);
                question.setUserAnswer(userAnswer);
                index++;
            }
            result = analyzer.analyze(test);
        } while (view.showResult(result));
    }
}
