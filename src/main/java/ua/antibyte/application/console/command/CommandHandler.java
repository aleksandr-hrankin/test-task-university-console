package ua.antibyte.application.console.command;

public interface CommandHandler {
    String getName();

    String getCommandDescription();

    void executeCommand();
}
