package ru.denku.csv;

import org.junit.Before;
import org.junit.Test;
import ru.denku.entity.Answer;
import ru.denku.entity.Question;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CsvMapperTest {

    private Parser parser;

    private Mapper mapper;

    @Test(expected = NullPointerException.class)
    public void getQuestions_NullPointer() {
        mapper = new CsvMapper(null);
    }

    @Before
    public void setUp() {
        parser = mock(Parser.class);
        mapper = new CsvMapper(parser);
    }

    @Test
    public void getQuestions_ShouldBeEmpty() {
        Question actual = mapper.getQuestion(null);
        verify(parser).parse(null);
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void getQuestions_FewParams_ShouldBeEmpty() {
        String parseStr = "1,2,3";
        when(parser.parse(parseStr)).thenReturn(new ArrayList<>());
        Question actual = mapper.getQuestion(parseStr);
        verify(parser).parse(parseStr);
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void getQuestions_ShouldBeReturnOneQuestion() {
        List<String> results = new ArrayList<>();
        results.add("q");
        results.add("a1");
        results.add("a2");
        results.add("1");
        when(parser.parse(anyString())).thenReturn(results);

        Question actual = mapper.getQuestion(anyString());
        verify(parser).parse(anyString());
        assertThat(actual.getId(), is(1));
        assertThat(actual.getText(), is("q"));
        assertThat(actual.getCorrectAnswer(), is("1"));
        assertThat(actual.getAnswers().size(), is(2));
        for (int i = 0; i < actual.getAnswers().size(); i++) {
            Answer answer = actual.getAnswers().get(i);
            assertThat(answer.getId(), is(i + 1));
            assertThat(answer.getText(), is("a" + (i + 1)));
        }
    }

//    @Test
//    public void getQuestions_ShouldBeReturnTwoQuestions() {
//        mapper = new CsvMapper(parser, loader);
//        List<String> lines = new ArrayList<>();
//        lines.add("\"Question1\",\"Answer1\",\"Answer2\",\"1\"");
//        lines.add("\"Question2\",\"Answer1\",\"Answer2\",\"2\"");
//        when(loader.load()).thenReturn(lines);
//
//
//        List<Question> actual = mapper.getQuestions();
//        verify(loader).load();
//        assertThat(actual.size(), is(2));
//
//        Question question1 = actual.get(0);
//        assertThat(question1.getId(), is(1));
//        assertThat(question1.getText(), is("Question1"));
//        assertThat(question1.getCorrectAnswer(), is("1"));
//        assertThat(question1.getAnswers().size(), is(2));
//        List<Answer> answers = question1.getAnswers();
//        Answer answer1 = answers.get(0);
//        assertThat(answer1.getId(), is(1));
//        assertThat(answer1.getText(), is("Answer1"));
//        Answer answer2 = answers.get(1);
//        assertThat(answer2.getId(), is(2));
//        assertThat(answer2.getText(), is("Answer2"));
//
//        Question question2 = actual.get(1);
//        assertThat(question2.getId(), is(2));
//        assertThat(question2.getText(), is("Question2"));
//        assertThat(question2.getCorrectAnswer(), is("2"));
//        assertThat(question2.getAnswers().size(), is(2));
//        answers = question2.getAnswers();
//        answer1 = answers.get(0);
//        assertThat(answer1.getId(), is(1));
//        assertThat(answer1.getText(), is("Answer1"));
//        answer2 = answers.get(1);
//        assertThat(answer2.getId(), is(2));
//        assertThat(answer2.getText(), is("Answer2"));
//    }
}