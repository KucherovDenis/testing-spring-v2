package ru.denku.entity;

import java.util.*;

public class Question {
    private int id;

    private String text;

    private String correctAnswer;

    private String userAnswer;

    private List<Answer> answers = new ArrayList<>();

    public Question(int id, String text, String correctAnswer) {
        this.id = id;
        this.text = Objects.requireNonNull(text);
        this.correctAnswer = Objects.requireNonNull(correctAnswer);
        this.userAnswer = "";
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void shuffleAnswers() {
        Collections.shuffle(answers, new Random(answers.size()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id &&
                text.equals(question.text) &&
                correctAnswer.equals(question.correctAnswer) &&
                userAnswer.equals(question.userAnswer) &&
                answers.equals(question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, correctAnswer, userAnswer, answers);
    }
}
