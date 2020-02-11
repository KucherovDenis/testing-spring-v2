package ru.denku.domain;

import ru.denku.entity.QuestionDTO;
import ru.denku.entity.TestResult;
import ru.denku.io.ViewReader;
import ru.denku.io.ViewPrinter;

import java.util.List;
import java.util.Objects;

public class TestView implements View {

    private final ViewPrinter printer;

    private ViewReader reader;

    public TestView(ViewPrinter printer, ViewReader reader) {
        this.printer = printer;
        this.reader = Objects.requireNonNull(reader);
    }


    @Override
    public String showQuestion(int num, QuestionDTO question) {
        printer.printQuestion(num, question);
        String userAnswer;
        while (true) {
            try {
                List<Integer> answers = reader.readAnswer(1, question.getAnswers().size());
                userAnswer = QuestionUtils.answersMapById(question, answers);
                if (userAnswer.isEmpty()) {
                    printer.printInputError();
                } else {
                    break;
                }
            } catch (Exception e) {
                printer.printInputError();
            }
        }
        return userAnswer;
    }

    @Override
    public boolean showResult(TestResult result) {
        printer.printTestResult(result);
        return reader.confirmContinue();
    }
}
