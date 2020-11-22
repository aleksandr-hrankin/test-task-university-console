package ua.antibyte.application.console.command.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import ua.antibyte.application.console.command.CommandWorkingWithDepartment;
import ua.antibyte.application.entity.dto.StatisticsResponseDto;
import ua.antibyte.application.service.DepartmentService;

@Component
public class DepartmentStatisticsCommand extends CommandWorkingWithDepartment {
    private static final String COMMAND_NAME = "d-stats";
    private static final String DESCRIPTION = "show department statistic";
    private static final String CURSOR_NAME
            = "|main/" + COMMAND_NAME + "| Enter department name or [" + COMMAND_BACK + "]: ";

    public DepartmentStatisticsCommand(DepartmentService departmentService) {
        super(COMMAND_NAME, DESCRIPTION, CURSOR_NAME, departmentService);
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
            List<StatisticsResponseDto> statistics
                    = departmentService.getDepartmentStatistics(userValue);
            if (statistics.isEmpty()) {
                showErrorMessage("No statistics for " + userValue + " department.");
            }
            showResultMessage(statistics);
        }
    }

    private void showResultMessage(List<StatisticsResponseDto> statistics) {
        System.out.println();
        statistics.forEach(statistic -> System.out.println(statistic.getDegree()
                + " - "
                + statistic.getQuantity()));
    }
}
