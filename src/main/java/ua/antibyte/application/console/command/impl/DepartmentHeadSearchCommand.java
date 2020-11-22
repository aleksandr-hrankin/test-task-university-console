package ua.antibyte.application.console.command.impl;

import org.springframework.stereotype.Component;
import ua.antibyte.application.console.command.CommandWorkingWithDepartment;
import ua.antibyte.application.entity.Lecturer;
import ua.antibyte.application.exception.NoSuchElementException;
import ua.antibyte.application.service.DepartmentService;

@Component
public class DepartmentHeadSearchCommand extends CommandWorkingWithDepartment {
    private static final String COMMAND_NAME = "d-head";
    private static final String COMMAND_DESCRIPTION = "show head of department";
    private static final String CURSOR_NAME
            = "|main/" + COMMAND_NAME + "| Enter department name or [" + COMMAND_BACK + "]: ";

    public DepartmentHeadSearchCommand(DepartmentService departmentService) {
        super(COMMAND_NAME, COMMAND_DESCRIPTION, CURSOR_NAME, departmentService);
    }

    @Override
    public void executeCommand() {
        while (true) {
            showListDepartments();
            showCursor();
            String userValue = scanUserValue();
            if (userValue.equals(COMMAND_BACK)) {
                return;
            }
            try {
                Lecturer departmentHead = departmentService.getHeadOfDepartment(userValue);
                showResultMessage(userValue, departmentHead);
            } catch (NoSuchElementException e) {
                showErrorMessage("Department " + userValue + " not found.");
            }
        }
    }

    private void showResultMessage(String userValue, Lecturer departmentHead) {
        System.out.println();
        System.out.println("Head of " + userValue + " department is "
                + departmentHead.getLastName() + " " + departmentHead.getFirstName());
    }
}
