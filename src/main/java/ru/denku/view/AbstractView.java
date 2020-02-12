package ru.denku.view;

import ru.denku.io.*;


import java.util.Objects;

public abstract class AbstractView implements View {

    public static final String EMPTY_STRING = "";

    private final Printer printer;

    private final ConsoleReader reader;

    public AbstractView(Printer printer, ConsoleReader reader) {
        this.printer = printer;
        this.reader = Objects.requireNonNull(reader);
    }

    protected Printer getPrinter() {
        return printer;
    }

    protected ConsoleReader getReader() {
        return reader;
    }

    public void printInputError() {
        printer.printLine("Не корректный ввод ответа. Повторите ввод.", ConsoleColor.RED);
        printer.printLine("Введите варианты ответа:");
    }
}
