package ru.denku.io;

import ru.denku.entity.Answer;
import ru.denku.entity.QuestionDTO;
import ru.denku.entity.TestResult;

import java.util.List;
import java.util.Objects;

public class ViewPrinterImpl implements ViewPrinter {

    private Printer printer;

    private static final String EMPTY_STRING = "";

    public ViewPrinterImpl(Printer printer) {
        this.printer = Objects.requireNonNull(printer);
    }

    @Override
    public void printQuestion(int num, QuestionDTO question) {
        printer.printLine(String.format("Вопрос №%d %s",
                num,
                question.getText()
        ));
        printer.printLine(EMPTY_STRING);
        List<Answer> answers = question.getAnswers();
        for (int i = 0; i < answers.size(); i++) {
            printer.printLine(String.format("%d. %s", i + 1, answers.get(i).getText()));
        }
        printer.printLine(EMPTY_STRING);
        printer.printLine("Введите варианты ответа:");

    }

    @Override
    public void printInputError() {
        printer.printLine("Не корректный ввод ответа. Повторите ввод.", ConsoleColor.RED);
        printer.printLine("Введите варианты ответа:");
    }

    @Override
    public void printTestResult(TestResult testResult) {
        boolean isBad = testResult.isBad();
        ConsoleColor color = isBad ? ConsoleColor.RED : ConsoleColor.GREEN;
        if (!isBad) {
            printer.printLine("Поздравляем Вы успешно прошли тест.", color);
        } else {
            printer.printLine("Вам не удалось сдать тест.", color);
        }

        printer.printLine(String.format("Вы правильно ответили на %d из %d вопросов.",
                testResult.getCorrectAnswers(),
                testResult.getQuestionsCount()
        ), color);

        printer.printLine(String.format("Вы сделали %d ошибок из %d допустимых.",
                testResult.getQuestionsCount() - testResult.getCorrectAnswers(),
                testResult.getNumbersOfError()
        ), color);
        printer.printLine(EMPTY_STRING);
        int index = 1;
        for (boolean answerResult : testResult.getAnswerResult()) {
            if (answerResult) {
                printer.printLine(String.format("Вопрос №%d - правильно", index), ConsoleColor.GREEN);
            } else {
                printer.printLine(String.format("Вопрос №%d - не правильно", index), ConsoleColor.RED);
            }
            index++;
        }
        printer.printLine(EMPTY_STRING);
        if (!isBad) {
            printer.printLine("Попробуете закрепить знания?");
        } else {
            printer.printLine("Повторить попытку?");
        }
        printer.printLine("Да (y), Нет (n)");
    }
}
