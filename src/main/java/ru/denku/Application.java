package ru.denku;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.denku.domain.Runner;


@Configuration
@ComponentScan(basePackages = "ru.denku.*")
@PropertySource("classpath:application.properties")
public class Application {

    private static void showExceptionMessage(Exception e) {
        System.err.println(e.getMessage());
        Throwable cause = e.getCause();
        while (cause != null) {
            System.err.println(cause.getMessage());
            cause = cause.getCause();
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Application.class);
        context.refresh();

        Runner testRunner = context.getBean(Runner.class);
        try {
            testRunner.run();
        } catch (ApplicationException e) {
            showExceptionMessage(e);
        }
        context.close();
    }
}
