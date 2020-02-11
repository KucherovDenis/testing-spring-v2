package ru.denku.domain;

import ru.denku.entity.Test;
import ru.denku.entity.TestResult;

public interface Analyzer {
    TestResult analyze(Test test);
}
