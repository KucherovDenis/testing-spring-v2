package ru.denku.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.denku.csv.Mapper;
import ru.denku.domain.*;
import ru.denku.io.Loader;

@Configuration
public class TestServiceConfig {
    @Bean
    Repository questionsRepository(Loader loader, Mapper mapper) {
        return new QuestionsRepository(loader, mapper);
    }

    @Bean
    Creator testCreator(@Value("${test.questionsCount}") int questionsCount,
                        @Value("${test.numbersOfErrors}") int numbersOfErrors,
                        Repository repository) {
        return new TestCreator(questionsCount, numbersOfErrors, repository);
    }

    @Bean
    Analyzer testAnalyzer() {
        return new TestAnalyzer();
    }

    @Bean
    Runner testService(Creator creator,
                       View view,
                       Analyzer analyzer) {
        return new TestService(creator, view, analyzer);
    }
}
