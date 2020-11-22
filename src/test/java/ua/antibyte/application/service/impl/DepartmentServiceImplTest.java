package ua.antibyte.application.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.antibyte.application.entity.Department;
import ua.antibyte.application.entity.Lecturer;
import ua.antibyte.application.exception.NoSuchElementException;
import ua.antibyte.application.repository.DepartmentRepository;
import ua.antibyte.application.service.DepartmentService;

@SpringBootTest
class DepartmentServiceImplTest {
    private static final Lecturer LECTURER = new Lecturer("Bob", "Alison");
    private static final Department DEPARTMENT = new Department(LECTURER);

    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    void getDepartmentHeadTest() throws NoSuchElementException {
        Mockito.doReturn(DEPARTMENT)
                .when(departmentRepository)
                .findByName("math");

        Lecturer actualLecturer = departmentService.getHeadOfDepartment("math");
        assertEquals(LECTURER, actualLecturer);
    }

    @Test
    void getDepartmentHeadWithWrongDepartmentNameTest() {
        Mockito.doReturn(DEPARTMENT)
                .when(departmentRepository)
                .findByName("math");

        assertThrows(NoSuchElementException.class, () -> {
            departmentService.getHeadOfDepartment("wrong");
        });
    }

    @Test
    void getAverageSalaryOfDepartmentTest() throws NoSuchElementException {
        BigDecimal averageSalary = new BigDecimal("1500.29304829038409238490283409");
        Mockito.doReturn(averageSalary)
                .when(departmentRepository)
                .getAverageSalaryForDepartment("math");

        BigDecimal expectedAverageSalary = averageSalary.setScale(2, RoundingMode.CEILING);
        BigDecimal actualAverageSalary = departmentService.getAverageSalaryOfDepartment("math");
        assertEquals(expectedAverageSalary, actualAverageSalary);
    }

    @Test
    void getAverageSalaryOfDepartmentWithWrongDepartmentNameTest() throws NoSuchElementException {
        BigDecimal averageSalary = new BigDecimal("1500.29304829038409238490283409");
        Mockito.doReturn(averageSalary)
                .when(departmentRepository)
                .getAverageSalaryForDepartment("math");

        assertThrows(NoSuchElementException.class, () -> {
            departmentService.getAverageSalaryOfDepartment("wrong");
        });
    }
}
