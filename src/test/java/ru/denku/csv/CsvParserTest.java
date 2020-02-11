package ru.denku.csv;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class CsvParserTest {

    private Parser parser;

    @Before
    public void setUp() {
        parser = new CsvParser();
    }

    @Test
    public void parse_StringIsEmpty_ShouldBeReturnEmptyList() {
        String testString = "";
        assertEquals(0, parser.parse(testString).size());
    }

    @Test
    public void parse_StringIsNull_ShouldBeReturnEmptyList() {
        String testString = null;
        assertEquals(0, parser.parse(testString).size());
    }

    @Test
    public void parse_StringNotQuote_ShouldBeReturnList() {
        String testString = "1,2";
        List<String> actual = parser.parse(testString);
        assertThat(actual.size(), is(2));
        assertThat(actual.get(0), is("1"));
        assertThat(actual.get(1), is("2"));
    }

    @Test
    public void parse_StringQuote_ShouldBeReturnList() {
        String testString = "\"1\",\"2\"";
        List<String> actual = parser.parse(testString);
        assertThat(actual.size(), is(2));
        assertThat(actual.get(0), is("1"));
        assertThat(actual.get(1), is("2"));
    }

    @Test
    public void parse_StringDoubleQuote_ShouldBeReturnList() {
        String testString = "\"Aus\"\"tralia\",\"2\"";
        List<String> actual = parser.parse(testString);
        assertThat(actual.size(), is(2));
        assertThat(actual.get(0), is("Aus\"tralia"));
        assertThat(actual.get(1), is("2"));
    }

    @Test
    public void parse_StringNotFirstQuote_ShouldBeReturnList() {
        String testString = "Aus\"\"tralia\",\"2\"";
        List<String> actual = parser.parse(testString);
        assertThat(actual.size(), not(2));
    }
}