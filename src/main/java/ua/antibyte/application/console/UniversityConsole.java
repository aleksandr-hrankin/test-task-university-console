package ua.antibyte.application.console;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.antibyte.application.console.command.CommandHandler;

@Component
public class UniversityConsole {
    private static final String CURSOR_NAME = "|main| Enter command or [h]: ";
    private static final String UNKNOWN_COMMAND_MESSAGE
            = "Unknown command. [h] - help; [q] - close application";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Map<String, CommandHandler> COMMANDS = new HashMap<>();

    @Autowired
    private List<CommandHandler> commandHandlers;

    @PostConstruct
    private void fillCommands() {
        for (CommandHandler command : commandHandlers) {
            COMMANDS.put(command.getName(), command);
        }
    }

    public void run() {
        while (true) {
            showCursor();
            executeCommand(SCANNER.next());
        }
    }

    private void showCursor() {
        System.out.println();
        System.out.print(CURSOR_NAME);
    }

    private void executeCommand(String command) {
        try {
            COMMANDS.get(command).executeCommand();
        } catch (Exception e) {
            showMessageWrongCommand();
        }
    }

    private void showMessageWrongCommand() {
        System.out.println();
        System.out.println(UNKNOWN_COMMAND_MESSAGE);
    }
}
