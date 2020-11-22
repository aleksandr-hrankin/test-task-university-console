package ua.antibyte.application.service;

import java.math.BigDecimal;
import java.util.List;
import ua.antibyte.application.entity.Department;
import ua.antibyte.application.entity.Lecturer;
import ua.antibyte.application.entity.dto.StatisticsResponseDto;
import ua.antibyte.application.exception.NoSuchElementException;

public interface DepartmentService {
    void saveAll(List<Department> departments);

    List<Department> findAll();

    Lecturer getHeadOfDepartment(String departmentName) throws NoSuchElementException;

    List<StatisticsResponseDto> getDepartmentStatistics(String departmentName);

    BigDecimal getAverageSalaryOfDepartment(String departmentName) throws NoSuchElementException;

    int getNumberOfEmployeesOfDepartment(String departmentName) throws NoSuchElementException;
}
