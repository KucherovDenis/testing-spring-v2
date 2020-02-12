package ru.denku.view;

import java.util.HashMap;
import java.util.Map;

abstract class ViewValue {

    private Map<String, Object> values = new HashMap<>();

    protected Map<String, Object> getValues() {
        return values;
    }

    public void add(String name, Object value) {
        values.put(name, value);
    }

    public Object get(String name) {
        return values.get(name);
    }

    public boolean isEmpty() {
        return values.size() == 0;
    }
}
