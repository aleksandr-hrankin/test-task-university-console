package ua.antibyte.application.console.command.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import ua.antibyte.application.console.command.CommandHandler;

@Component
public class HelpCommand implements CommandHandler {
    private static final String COMMAND_NAME = "h";
    private static final String COMMAND_DESCRIPTION = "help";
    private final List<CommandHandler> commandHandlers;

    public HelpCommand(List<CommandHandler> commandHandlers) {
        this.commandHandlers = commandHandlers;
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
        System.out.println();
        System.out.println("[" + COMMAND_NAME + "] - " + COMMAND_DESCRIPTION);
        commandHandlers.forEach(commandHandler -> System.out.println("["
                + commandHandler.getName() + "] - " + commandHandler.getCommandDescription()));
    }
}
