package ru.denku.io;

public class ConsolePrinter implements Printer {
    @Override
    public void printLine(String message) {
        System.out.println(message);
    }

    @Override
    public void printLine(String message, ConsoleColor color) {
        System.out.print(color);
        System.out.println(message);
        System.out.print(ConsoleColor.RESET);
    }
}
