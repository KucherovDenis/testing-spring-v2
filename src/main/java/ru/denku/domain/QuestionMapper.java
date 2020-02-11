package ru.denku.domain;

import ru.denku.entity.Question;
import ru.denku.entity.QuestionDTO;

public class QuestionMapper {
    public static QuestionDTO toDTO(Question question) {
        return new QuestionDTO(question.getId(), question.getText(), question.getAnswers());
    }
}
