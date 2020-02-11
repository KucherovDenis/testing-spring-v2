package ru.denku.io;

import ru.denku.ApplicationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReaderImpl implements ConsoleReader {

    private final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

    protected BufferedReader getReader() {
        return consoleReader;
    }

    @Override
    public String readString() {
        try {
            return getReader().readLine();
        } catch (IOException e) {
            throw new ApplicationException("Ошибка чтения данных.", e);
        }
    }
}
