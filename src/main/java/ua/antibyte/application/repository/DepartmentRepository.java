package ua.antibyte.application.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.antibyte.application.entity.Department;
import ua.antibyte.application.entity.dto.StatisticsResponseDto;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String departmentName);

    @Query("select "
            + "new ua.antibyte.application.entity.dto.StatisticsResponseDto(l.degree, count(l)) "
            + "from Department d join d.lecturers l "
            + "where d.name = ?1 "
            + "group by l.degree ")
    List<StatisticsResponseDto> getStatisticsForDepartment(String departmentName);

    @Query("select new java.math.BigDecimal(avg(l.salary)) "
            + "from Department d join d.lecturers l "
            + "where d.name = ?1 ")
    BigDecimal getAverageSalaryForDepartment(String departmentName);

    @Query("select count(l) "
            + "from Department d join d.lecturers l "
            + "where d.name = ?1 ")
    int getCountOfEmployeeForDepartment(String departmentName);
}
