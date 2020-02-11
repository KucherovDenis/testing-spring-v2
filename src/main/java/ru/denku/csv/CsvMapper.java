package ru.denku.csv;

import ru.denku.entity.Answer;
import ru.denku.entity.Question;

import java.util.List;
import java.util.Objects;

public class CsvMapper implements Mapper {

    private Parser parser;

    private int questionID;

    public CsvMapper(Parser parser) {
        this.parser = Objects.requireNonNull(parser);
        questionID = 1;
    }

    @Override
    public Question getQuestion(String params) {
        Question question = null;
        List<String> columns = parser.parse(params);
        //если есть вопрос, 2 варианта ответов и ответ
        if (columns.size() >= 4) {
            question = new Question(questionID, columns.get(0), columns.get(columns.size() - 1));
            int answerID = 1;
            for (int i = 1; i < columns.size() - 1; i++) {
                String answerText = columns.get(i);
                Answer answer = new Answer(answerID, answerText);
                question.addAnswer(answer);
                answerID++;
            }
            questionID++;
        }

        return question;
    }
}
