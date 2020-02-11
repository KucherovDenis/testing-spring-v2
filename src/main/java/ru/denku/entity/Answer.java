package ru.denku.entity;

import java.util.Objects;

public class Answer {
    private int id;

    private String text;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Answer(int id, String text) {
        this.id = id;
        this.text = Objects.requireNonNull(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id &&
                text.equals(answer.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}
