package ru.denku.domain;

import ru.denku.entity.Question;
import ru.denku.io.Loader;
import ru.denku.csv.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionsRepository implements Repository {

    private final Loader loader;

    private final Mapper mapper;

    private final List<Question> questions = new ArrayList<>();

    public QuestionsRepository(Loader loader, Mapper mapper) {
        this.loader = Objects.requireNonNull(loader);
        this.mapper = Objects.requireNonNull(mapper);
    }

    protected List<Question> getQuestions() {
        return  questions;
    }

    protected void loadQuestions() {
        List<String> lines = loader.load();
        for (String line : lines) {
            Question question = mapper.getQuestion(line);
            if (question != null) {
                questions.add(question);
            }
        }
    }

    @Override
    public void load() {
        loadQuestions();
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Question getQuestion(int id) {
        return questions.
                stream().
                filter(q -> q.getId() == id).
                findFirst().orElse(null);
    }
}
