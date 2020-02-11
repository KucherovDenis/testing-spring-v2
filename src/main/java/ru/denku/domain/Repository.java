package ru.denku.domain;

import ru.denku.entity.Question;

public interface Repository {
    void load();
    int getCount();
    Question getQuestion(int id);
}
