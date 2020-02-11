package ru.denku.domain;

import ru.denku.entity.QuestionDTO;
import ru.denku.entity.TestResult;

public interface View {
    String showQuestion(int num, QuestionDTO question);
    boolean showResult(TestResult result);
}
