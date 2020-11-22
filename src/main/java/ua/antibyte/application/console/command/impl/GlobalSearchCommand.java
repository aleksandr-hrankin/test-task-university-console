package ua.antibyte.application.console.command.impl;

import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import org.springframework.stereotype.Component;
import ua.antibyte.application.console.command.CommandHandler;
import ua.antibyte.application.entity.Lecturer;
import ua.antibyte.application.service.LecturerService;

@Component
public class GlobalSearchCommand implements CommandHandler {
    private static final String COMMAND_NAME = "s";
    private static final String COMMAND_DESCRIPTION = "global search by template";
    private static final String CURSOR_NAME
            = "|main/" + COMMAND_NAME + "| Enter template or [back]: ";
    private static final String COMMAND_BACK = "back";
    private static final Scanner SCANNER = new Scanner(System.in);
    private final LecturerService lecturerService;

    public GlobalSearchCommand(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }

    @Override
    public void executeCommand() {
        while (true) {
            showCursor();
            String userValue = SCANNER.next();
            if (userValue.equals(COMMAND_BACK)) {
                return;
            }
            List<Lecturer> searchResults = lecturerService.findByTemplate(userValue);
            if (searchResults.isEmpty()) {
                showErrorMessage();
            }
            showResultMessage(searchResults);
        }
    }

    private void showCursor() {
        System.out.println();
        System.out.print(CURSOR_NAME);
    }

    private void showErrorMessage() {
        System.out.println();
        System.out.println("No matches found.");
    }

    private void showResultMessage(List<Lecturer> searchResults) {
        StringJoiner lecturerNames = new StringJoiner(", ");
        searchResults.forEach(lecturer
                -> lecturerNames.add(lecturer.getLastName() + " " + lecturer.getFirstName()));
        System.out.println(lecturerNames.toString());
    }
}
