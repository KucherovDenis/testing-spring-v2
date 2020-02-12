package ru.denku.domain;

import ru.denku.ApplicationException;
import ru.denku.entity.Question;
import ru.denku.entity.QuestionDTO;
import ru.denku.entity.Test;
import ru.denku.entity.TestResult;
import ru.denku.view.View;
import ru.denku.view.ViewArgs;
import ru.denku.configs.ViewFactory;
import ru.denku.view.ViewResult;

import java.util.Objects;

public class TestService implements Runner {

    private final Creator creator;

    private final ViewFactory viewFactory;

    private final Analyzer analyzer;

    public TestService(Creator creator, ViewFactory viewFactory, Analyzer analyzer) {
        this.creator = creator;
        this.viewFactory = Objects.requireNonNull(viewFactory);
        this.analyzer = Objects.requireNonNull(analyzer);
        creator.prepare();
    }

    private String showQuestion(int index, QuestionDTO questionDTO) {
        ViewArgs args = new ViewArgs();
        args.add("num", index);
        args.add("question", questionDTO);

        View view = viewFactory.getView("question");
        if (view == null) {
            throw new ApplicationException("Не найдено отображение question.");
        }

        ViewResult viewResult = view.showView(args);
        return (String) viewResult.get("userAnswer");
    }

    private boolean showResult(TestResult result) {
        ViewArgs args = new ViewArgs();
        args.add("testResult", result);

        View view = viewFactory.getView("result");
        if (view == null) {
            throw new ApplicationException("Не найдено отображение result.");
        }

        ViewResult viewResult = view.showView(args);
        return (Boolean) viewResult.get("confirm");
    }

    @Override
    public void run() {
        TestResult result;
        do {
            Test test = creator.get();
            int index = 1;
            for (Question question : test.getQuestions()) {
                QuestionDTO questionDTO = QuestionMapper.toDTO(question);
                String userAnswer = showQuestion(index, questionDTO);
                question.setUserAnswer(userAnswer);
                index++;
            }
            result = analyzer.analyze(test);
        } while (showResult(result));
    }
}
