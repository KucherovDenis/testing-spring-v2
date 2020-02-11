package ru.denku.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionDTO {
    private int id;

    private String text;

    private List<Answer> answers = new ArrayList<>();

    public QuestionDTO(int id, String text, List<Answer> answers) {
        this.id = id;
        this.text = text;
        this.answers.addAll(answers);
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
