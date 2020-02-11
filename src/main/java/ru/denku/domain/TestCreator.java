package ru.denku.domain;

import ru.denku.ApplicationException;
import ru.denku.entity.Question;
import ru.denku.entity.Test;

import java.util.*;

public class TestCreator implements Creator {

    private final int questionsCount;

    private final Repository repository;

    private final int numberOfErrors;

    public TestCreator(int questionsCount, int numberOfErrors, Repository repository) {
        if (questionsCount <= 0)
            throw new ApplicationException("Значение поля questionCount должно быть больше нуля.");

        if (numberOfErrors <= 0 || numberOfErrors >= questionsCount)
            throw new ApplicationException("Не допустимое значение поля numberOfErrors.");

        this.questionsCount = questionsCount;
        this.repository = Objects.requireNonNull(repository);
        this.numberOfErrors = numberOfErrors;
    }

    @Override
    public void prepare() {
        repository.load();
        if (questionsCount > repository.getCount()) {
            throw new ApplicationException("Не возможно создать тест число вопрос в репозитории меньше желаемого.");
        }
    }

    protected int[] getRandomQuestionsID() {
        Random random = new Random();
        return random.ints(1, repository.getCount() + 1).
                distinct().
                limit(questionsCount).
                toArray();
    }

    protected Test createTest() {
        Test test = new Test(numberOfErrors);

        int[] questionsID = getRandomQuestionsID();

        for (int id : questionsID) {
            Question question = repository.getQuestion(id);
            if (question != null) {
                question.shuffleAnswers();
                test.addQuestion(question);
            }

        }

        if (test.getQuestions().size() != questionsCount) {
            throw new ApplicationException("Проиизошла критическая ошибка при создании теста. Приложение будет закрыто.");
        }

        return test;
    }

    @Override
    public Test get() {
        return createTest();
    }
}
