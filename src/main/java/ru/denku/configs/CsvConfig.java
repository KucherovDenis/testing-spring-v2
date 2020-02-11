package ru.denku.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.denku.csv.CsvMapper;
import ru.denku.csv.CsvParser;
import ru.denku.csv.Mapper;
import ru.denku.csv.Parser;
import ru.denku.io.FileLoader;
import ru.denku.io.Loader;

@Configuration
public class CsvConfig {

    @Bean
    Loader fileLoader(@Value("${loader.fileName}") String fileName) {
        return new FileLoader(fileName);
    }

    @Bean
    Parser csvParser() {
        return new CsvParser();
    }

    @Bean
    Mapper csvMapper(Parser parser) {
        return new CsvMapper(parser);
    }
}
