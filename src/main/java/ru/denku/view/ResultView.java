package ru.denku.view;

import ru.denku.ApplicationException;
import ru.denku.entity.TestResult;
import ru.denku.io.ConsoleColor;
import ru.denku.io.ConsoleReader;
import ru.denku.io.Printer;

public class ResultView extends AbstractView {
    public ResultView(Printer printer, ConsoleReader reader) {
        super(printer, reader);
    }

    private void printTestResult(TestResult testResult) {
        boolean isBad = testResult.isBad();
        ConsoleColor color = isBad ? ConsoleColor.RED : ConsoleColor.GREEN;
        if (!isBad) {
            getPrinter().printLine("Поздравляем Вы успешно прошли тест.", color);
        } else {
            getPrinter().printLine("Вам не удалось сдать тест.", color);
        }

        getPrinter().printLine(String.format("Вы правильно ответили на %d из %d вопросов.",
                testResult.getCorrectAnswers(),
                testResult.getQuestionsCount()
        ), color);

        getPrinter().printLine(String.format("Вы сделали %d ошибок из %d допустимых.",
                testResult.getQuestionsCount() - testResult.getCorrectAnswers(),
                testResult.getNumbersOfError()
        ), color);
        getPrinter().printLine(EMPTY_STRING);
        int index = 1;
        for (boolean answerResult : testResult.getAnswerResult()) {
            if (answerResult) {
                getPrinter().printLine(String.format("Вопрос №%d - правильно", index), ConsoleColor.GREEN);
            } else {
                getPrinter().printLine(String.format("Вопрос №%d - не правильно", index), ConsoleColor.RED);
            }
            index++;
        }
        getPrinter().printLine(EMPTY_STRING);
        if (!isBad) {
            getPrinter().printLine("Попробуете закрепить знания?");
        } else {
            getPrinter().printLine("Повторить попытку?");
        }
        getPrinter().printLine("Да (y), Нет (n)");
    }

    public boolean confirmContinue() {
        String answer = getReader().readString();
        return "y".equals(answer.toLowerCase());
    }

    @Override
    public ViewResult showView(ViewArgs args) {
        if (args.isEmpty()) throw new ApplicationException("Не заданы входные параметры.");
        TestResult testResult = (TestResult) args.get("testResult");
        if (testResult == null) throw new ApplicationException("Не задан параметр testResult.");

        printTestResult(testResult);
        boolean confirm = confirmContinue();
        ViewResult result = new ViewResult();
        result.add("confirm", confirm);
        return result;
    }
}
