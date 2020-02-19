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

    private void printTestResult(Printer printer, TestResult testResult) {
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
    }

    public boolean confirmContinue(Printer printer, ConsoleReader reader) {
        printer.printLine("Да (y), Нет (n)");
        String answer = reader.readString();
        return "y".equals(answer.toLowerCase());
    }

    @Override
    protected void doWrite(Printer printer, ViewArgs args) {
        if (args.isEmpty()) throw new ApplicationException("Не заданы входные параметры.");
        TestResult testResult = (TestResult) args.get("testResult");
        if (testResult == null) throw new ApplicationException("Не задан параметр testResult.");

        printTestResult(printer, testResult);
    }

    @Override
    protected ViewResult doRead(Printer printer, ConsoleReader reader) {
        boolean confirm = confirmContinue(printer, reader);
        ViewResult result = new ViewResult();
        result.add("confirm", confirm);
        return result;
    }
}
