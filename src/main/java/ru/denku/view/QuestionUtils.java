package ru.denku.view;

import ru.denku.entity.Answer;
import ru.denku.entity.QuestionDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

class QuestionUtils {
    public static String answersMapById(QuestionDTO question, List<Integer> userAnswers) {
        List<Integer> idList = new ArrayList<>();
        for (int userAnswer : userAnswers) {
            Answer answer = question.getAnswers().get(userAnswer - 1);
            idList.add(answer.getId());
        }

        Collections.sort(idList);
        StringJoiner sj = new StringJoiner("");
        idList.forEach(i -> sj.add(i.toString()));
        return sj.toString();
    }
}