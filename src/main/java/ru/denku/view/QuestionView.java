package ru.denku.view;

import ru.denku.ApplicationException;
import ru.denku.entity.Answer;
import ru.denku.entity.QuestionDTO;
import ru.denku.io.ConsoleReader;
import ru.denku.io.Printer;


import java.util.ArrayList;
import java.util.List;

public class QuestionView extends AbstractView {

    public QuestionView(Printer printer, ConsoleReader reader) {
        super(printer, reader);
    }

    private void printQuestion(int num, QuestionDTO question) {
        getPrinter().printLine(String.format("Вопрос №%d %s",
                num,
                question.getText()
        ));
        getPrinter().printLine(EMPTY_STRING);
        List<Answer> answers = question.getAnswers();
        for (int i = 0; i < answers.size(); i++) {
            getPrinter().printLine(String.format("%d. %s", i + 1, answers.get(i).getText()));
        }
        getPrinter().printLine(EMPTY_STRING);
        getPrinter().printLine("Введите варианты ответа:");

    }

    private List<Integer> readAnswer(int min, int max) {
        List<Integer> answers = new ArrayList<>();
        String answersStr = getReader().readString();
        for (char c : answersStr.toCharArray()) {
            int userAnswer = Integer.parseInt(Character.toString(c));
            if(userAnswer >= min && userAnswer <= max) {
                answers.add(userAnswer);
            }
        }
        return answers;
    }

    @Override
    public ViewResult showView(ViewArgs args) {
        if (args.isEmpty()) throw new ApplicationException("Не заданы входные параметры.");

        int num = (Integer) args.get("num");
        QuestionDTO question = (QuestionDTO) args.get("question");
        if (question == null) throw new ApplicationException("Не задан параметр question.");

        printQuestion(num, question);
        String userAnswer;
        while (true) {
            try {
                List<Integer> answers = readAnswer(1, question.getAnswers().size());
                userAnswer = QuestionUtils.answersMapById(question, answers);
                if (userAnswer.isEmpty()) {
                    printInputError();
                } else {
                    break;
                }
            } catch (Exception e) {
                printInputError();
            }
        }

        ViewResult result = new ViewResult();
        result.add("userAnswer", userAnswer);
        return result;
    }
}
