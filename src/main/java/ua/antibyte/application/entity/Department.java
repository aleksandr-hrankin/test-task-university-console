package ua.antibyte.application.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departments")
@NoArgsConstructor
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id")
    private List<Lecturer> lecturers;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    public Department(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}
