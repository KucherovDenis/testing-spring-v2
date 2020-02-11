package ru.denku.domain;

import ru.denku.entity.Test;

public interface Creator {
    void prepare();
    Test get();
}
