package ru.denku.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.denku.view.QuestionView;
import ru.denku.view.ResultView;
import ru.denku.view.View;
import ru.denku.io.*;

@Configuration
public class ViewConfig {
    @Bean
    ConsoleReader consoleReader() {
        return new ConsoleReaderImpl();
    }

    @Bean
    Printer consolePrinter() {
        return new ConsolePrinter();
    }

    @Bean(name = "question")
    View questionView(Printer printer, ConsoleReader reader) {
        return new QuestionView(printer, reader);
    }

    @Bean(name = "result")
    View resultView(Printer printer, ConsoleReader reader) {
        return new ResultView(printer, reader);
    }
}
