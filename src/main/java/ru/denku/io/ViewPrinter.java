package ru.denku.io;

import ru.denku.entity.QuestionDTO;
import ru.denku.entity.TestResult;

public interface ViewPrinter {
    void printQuestion(int num, QuestionDTO question);
    void printInputError();
    void printTestResult(TestResult testResult);
}
