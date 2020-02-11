package ru.denku.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.denku.domain.TestView;
import ru.denku.domain.View;
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

    @Bean
    ViewReader viewReader(ConsoleReader reader) {
        return new ViewReaderImpl(reader);
    }

    @Bean
    ViewPrinter viewPrinter(Printer printer) {
        return new ViewPrinterImpl(printer);
    }

    @Bean
    View testView(ViewPrinter printer, ViewReader reader) {
        return new TestView(printer, reader);
    }
}
