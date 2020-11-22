package ua.antibyte.application.console.command.impl;

import org.springframework.stereotype.Component;
import ua.antibyte.application.console.command.CommandWorkingWithDepartment;
import ua.antibyte.application.exception.NoSuchElementException;
import ua.antibyte.application.service.DepartmentService;

@Component
public class NumberOfEmployeesOfDepartmentCommand extends CommandWorkingWithDepartment {
    private static final String COMMAND_NAME = "d-num";
    private static final String COMMAND_DESCRIPTION = "show count of employees for department";
    private static final String CURSOR_NAME
            = "|main/" + COMMAND_NAME + "| Enter department name or [" + COMMAND_BACK + "]: ";

    public NumberOfEmployeesOfDepartmentCommand(DepartmentService departmentService) {
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
                int numberOfEmployeesOfDepartment
                        = departmentService.getNumberOfEmployeesOfDepartment(userValue);
                showResultMessage(numberOfEmployeesOfDepartment);
            } catch (NoSuchElementException e) {
                showErrorMessage("Department " + userValue + " not found.");
            }
        }
    }

    private void showResultMessage(int numberOfEmployeesOfDepartment) {
        System.out.println();
        System.out.println(numberOfEmployeesOfDepartment);
    }
}
