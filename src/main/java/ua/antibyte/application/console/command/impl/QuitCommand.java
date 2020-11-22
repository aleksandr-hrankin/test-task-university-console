package ua.antibyte.application.console.command.impl;

import org.springframework.stereotype.Component;
import ua.antibyte.application.console.command.CommandHandler;

@Component
public class QuitCommand implements CommandHandler {
    private static final String COMMAND_NAME = "q";
    private static final String COMMAND_DESCRIPTION = "quit";

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
        System.exit(0);
    }
}
