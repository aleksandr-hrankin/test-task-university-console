package ua.antibyte.application.console.command;

import java.util.Scanner;
import ua.antibyte.application.service.DepartmentService;

public abstract class CommandWorkingWithDepartment implements CommandHandler {
    protected static final String COMMAND_BACK = "back";
    private static final Scanner SCANNER = new Scanner(System.in);
    protected final DepartmentService departmentService;
    private final String commandName;
    private final String commandDescription;
    private final String cursorName;

    public CommandWorkingWithDepartment(String commandName,
                                        String commandDescription,
                                        String cursorName,
                                        DepartmentService departmentService) {
        this.commandName = commandName;
        this.commandDescription = commandDescription;
        this.cursorName = cursorName;
        this.departmentService = departmentService;
    }

    @Override
    public String getName() {
        return commandName;
    }

    @Override
    public String getCommandDescription() {
        return commandDescription;
    }

    protected void showErrorMessage(String message) {
        System.out.println();
        System.out.println(message);
    }

    protected void showListDepartments() {
        System.out.println();
        System.out.println("List of departments: ");
        departmentService.findAll().forEach(department
                -> System.out.println(" - [" + department.getName() + "]"));
    }

    protected void showCursor() {
        System.out.println();
        System.out.print(cursorName);
    }

    protected String scanUserValue() {
        return SCANNER.next();
    }
}
