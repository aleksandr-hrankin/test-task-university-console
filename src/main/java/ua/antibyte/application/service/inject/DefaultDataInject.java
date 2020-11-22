package ua.antibyte.application.service.inject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ua.antibyte.application.entity.Department;
import ua.antibyte.application.entity.Lecturer;
import ua.antibyte.application.service.DepartmentService;

@Component
public class DefaultDataInject {
    private static final String[] FIRST_NAMES_LECTURER = new String[]{
            "Alex", "Max", "Denis", "Petr", "Dmitriy",
            "Oleg", "Bob", "Jhon", "Aleksandr", "Boris",
            "Marva", "Charletter", "Rossie", "Roslyn",
            "Cathy", "Delmy", "Irvin", "Lorina", "Claire"
    };
    private static final String[] LAST_NAMES_LECTURER = new String[]{
            "Smith", "Johnson", "Williams", "Brown", "Jones",
            "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
            "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
            "Thomas", "Taylor", "Moore", "Jackson", "Martin"
    };
    private static final Lecturer.Degree[] DEGREE_LECTURER = Lecturer.Degree.values();
    private static final int MAX_SALARY_DOLLAR_LECTURER = 1500;
    private static final int MAX_SALARY_CENT_LECTURER = 100;
    private static final int NUMBER_OF_LECTURERS = 50;
    private static final Random RANDOM = new Random();
    private final DepartmentService departmentService;

    public DefaultDataInject(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostConstruct
    private void inject() {
        List<Lecturer> lecturers = getRandomLecturers();
        List<Department> departments = List.of(
                getDepartment("math", lecturers),
                getDepartment("physic", lecturers),
                getDepartment("chem", lecturers),
                getDepartment("program", lecturers),
                getDepartment("bio", lecturers)
        );
        departmentService.saveAll(departments);
    }

    private Department getDepartment(String name, List<Lecturer> lecturers) {
        Department department = new Department();
        department.setName(name);
        department.setLecturers(selectLecturersForDepartment(lecturers));
        department.setLecturer(lecturers.get(RANDOM.nextInt(lecturers.size())));
        return department;
    }

    private List<Lecturer> getRandomLecturers() {
        List<Lecturer> lecturers = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_LECTURERS; i++) {
            lecturers.add(getRandomLecturer());
        }
        return lecturers;
    }

    private List<Lecturer> selectLecturersForDepartment(List<Lecturer> lecturers) {
        List<Lecturer> lecturersForDepartment = new ArrayList<>();
        for (Lecturer lecturer : lecturers) {
            if (RANDOM.nextBoolean()) {
                lecturersForDepartment.add(lecturer);
            }
        }
        return lecturersForDepartment;
    }

    private Lecturer getRandomLecturer() {
        Lecturer lecturer = new Lecturer();
        lecturer.setFirstName(getRandomFirstName());
        lecturer.setLastName(getRandomLastName());
        lecturer.setDegree(getRandomDegree());
        lecturer.setSalary(getRandomSalary());
        return lecturer;
    }

    private String getRandomFirstName() {
        return FIRST_NAMES_LECTURER[RANDOM.nextInt(FIRST_NAMES_LECTURER.length)];
    }

    private String getRandomLastName() {
        return LAST_NAMES_LECTURER[RANDOM.nextInt(LAST_NAMES_LECTURER.length)];
    }

    private Lecturer.Degree getRandomDegree() {
        return DEGREE_LECTURER[RANDOM.nextInt(DEGREE_LECTURER.length)];
    }

    private BigDecimal getRandomSalary() {
        return new BigDecimal(
                RANDOM.nextInt(MAX_SALARY_DOLLAR_LECTURER)
                        + "."
                        + RANDOM.nextInt(MAX_SALARY_CENT_LECTURER)
        );
    }
}
