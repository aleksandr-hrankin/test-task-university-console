package ua.antibyte.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.antibyte.application.console.UniversityConsole;

@SpringBootApplication
public class TestTaskUniversityConsoleApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestTaskUniversityConsoleApplication.class, args);
        new UniversityConsole().run();
    }
}
