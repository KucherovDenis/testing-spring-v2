package ru.denku.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.denku.view.View;

import java.util.Map;

@Component
public class ViewFactory {
    @Autowired
    private Map<String, View> viewMap;

    public View getView(String name) {
        return viewMap.get(name);
    }
}
