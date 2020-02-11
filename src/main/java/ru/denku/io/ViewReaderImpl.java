package ru.denku.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewReaderImpl implements ViewReader {

    private ConsoleReader reader;

    public ViewReaderImpl(ConsoleReader reader) {
        this.reader = Objects.requireNonNull(reader);
    }

    @Override
    public List<Integer> readAnswer(int min, int max) {
        List<Integer> answers = new ArrayList<>();
        String answersStr = reader.readString();
        for (char c : answersStr.toCharArray()) {
            int userAnswer = Integer.parseInt(Character.toString(c));
            if(userAnswer >= min && userAnswer <= max) {
                answers.add(userAnswer);
            }
        }
        return answers;
    }

    @Override
    public boolean confirmContinue() {
        String answer = reader.readString();
        return "y".equals(answer.toLowerCase());
    }
}
