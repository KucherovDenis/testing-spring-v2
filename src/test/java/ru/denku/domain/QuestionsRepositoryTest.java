package ru.denku.domain;

import org.junit.Test;
import ru.denku.entity.Answer;
import ru.denku.entity.Question;
import ru.denku.io.Loader;
import ru.denku.csv.Mapper;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class QuestionsRepositoryTest {

    private Repository repository;

    private Loader loader = mock(Loader.class);
    private Mapper mapper = mock(Mapper.class);

    @Test
    public void loadQuestions_IfFileEmpry_RepositoryShouldBeEmpty() {
        when(loader.load()).thenReturn(new ArrayList<>());
        repository = new QuestionsRepository(loader, mapper);
        repository.load();
        verify(loader).load();
        assertEquals(0, repository.getCount());
    }

    @Test
    public void loadQuestions_IfFileNotEmpry_RepositoryShouldBeValue() {
        List<String> lines = new ArrayList<>();
        String line = "Fake";
        lines.add(line);
        when(loader.load()).thenReturn(lines);
        Question question = new Question(1, "Fake", "1");
        when(mapper.getQuestion(line)).thenReturn(question);

        repository = new QuestionsRepository(loader, mapper);
        repository.load();
        verify(loader).load();
        verify(mapper).getQuestion(line);
        assertEquals(1, repository.getCount());

        Question actual = repository.getQuestion(1);
        assertThat(actual.getId(), is(1));
        assertThat(actual.getText(), is("Fake"));
        assertThat(actual.getCorrectAnswer(), is("1"));
        assertThat(actual.getAnswers().size(), is(0));
    }

    @Test
    public void load() {
        repository = new FakeRepository(loader, mapper);
        repository.load();
        assertEquals(1, repository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void ifArgsIsNull_ShouldBeException() {
        repository = new QuestionsRepository(null, null);
    }

    @Test
    public void getCount_IfEmpty_ShouldBeZero() {
        repository = new QuestionsRepository(loader, mapper);
        assertEquals(0, repository.getCount());
    }

    @Test
    public void getQuestion_IfEmpty_ShouldBeNull() {
        repository = new QuestionsRepository(loader, mapper);
        assertNull(repository.getQuestion(1));
    }

    @Test
    public void getCount_IfLoad_ShouldBeOne() {
        repository = new FakeRepository(loader, mapper);
        repository.load();
        assertEquals(1, repository.getCount());
    }

    @Test
    public void getQuestion_IfLoadAndCorrectId_ShouldBeValue() {
        repository = new FakeRepository(loader, mapper);
        repository.load();
        Question question = repository.getQuestion(1);
        assertNotNull(question);
        assertEquals(1, question.getId());
    }

    @Test
    public void getQuestion_IfLoadAndIncorrectId_ShouldBeNull() {
        repository = new FakeRepository(loader, mapper);
        repository.load();
        Question question = repository.getQuestion(2);
        assertNull(question);
    }


    private static class FakeRepository extends QuestionsRepository {

        public FakeRepository(Loader loader, Mapper mapper) {
            super(loader, mapper);
        }

        @Override
        protected void loadQuestions() {
            Question question = new Question(1, "q1", "1");
            Answer answer = new Answer(1, "a1");
            question.addAnswer(answer);
            answer = new Answer(2, "a2");
            question.addAnswer(answer);
            getQuestions().add(question);

        }
    }
}