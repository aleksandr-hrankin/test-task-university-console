package ua.antibyte.application.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;
import ua.antibyte.application.entity.Department;
import ua.antibyte.application.entity.Lecturer;
import ua.antibyte.application.entity.dto.StatisticsResponseDto;
import ua.antibyte.application.exception.NoSuchElementException;
import ua.antibyte.application.repository.DepartmentRepository;
import ua.antibyte.application.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void saveAll(List<Department> departments) {
        departmentRepository.saveAll(departments);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Lecturer getHeadOfDepartment(String departmentName) throws NoSuchElementException {
        if (departmentRepository.findByName(departmentName) == null) {
            throw new NoSuchElementException();
        }
        return departmentRepository.findByName(departmentName).getLecturer();
    }

    @Override
    public List<StatisticsResponseDto> getDepartmentStatistics(String departmentName) {
        return departmentRepository.getStatisticsForDepartment(departmentName);
    }

    @Override
    public BigDecimal getAverageSalaryOfDepartment(String departmentName)
            throws NoSuchElementException {
        try {
            BigDecimal averageSalaryForDepartment
                    = departmentRepository.getAverageSalaryForDepartment(departmentName);
            return averageSalaryForDepartment.setScale(2, RoundingMode.CEILING);
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int getNumberOfEmployeesOfDepartment(String departmentName)
            throws NoSuchElementException {
        if (departmentRepository.findByName(departmentName) == null) {
            throw new NoSuchElementException();
        }
        return departmentRepository.getCountOfEmployeeForDepartment(departmentName);
    }
}
