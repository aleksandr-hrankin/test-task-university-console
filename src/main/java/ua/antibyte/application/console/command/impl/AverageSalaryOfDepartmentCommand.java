package ua.antibyte.application.console.command.impl;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import ua.antibyte.application.console.command.CommandWorkingWithDepartment;
import ua.antibyte.application.exception.NoSuchElementException;
import ua.antibyte.application.service.DepartmentService;

@Component
public class AverageSalaryOfDepartmentCommand extends CommandWorkingWithDepartment {
    private static final String COMMAND_NAME = "avg";
    private static final String COMMAND_DESCRIPTION = "show average salary for the department";
    private static final String CURSOR_NAME
            = "|main/" + COMMAND_NAME + "| Enter department name or [" + COMMAND_BACK + "]: ";

    public AverageSalaryOfDepartmentCommand(DepartmentService departmentService) {
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
                BigDecimal averageSalaryOfDepartment
                        = departmentService.getAverageSalaryOfDepartment(userValue);
                showResultMessage(userValue, averageSalaryOfDepartment);
            } catch (NoSuchElementException e) {
                showErrorMessage("Department " + userValue + " not found.");
            }
        }
    }

    private void showResultMessage(String userValue, BigDecimal averageSalaryOfDepartment) {
        System.out.println();
        System.out.println("The average salary of " + userValue
                + " is " + averageSalaryOfDepartment);
    }
}
