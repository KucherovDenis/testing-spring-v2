package ru.denku.io;

import java.util.List;

public interface ViewReader {
    List<Integer> readAnswer(int min, int max);
    boolean confirmContinue();
}
