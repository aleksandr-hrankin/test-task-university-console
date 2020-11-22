package ua.antibyte.application.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.antibyte.application.entity.Lecturer;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    @Query("select l "
            + "from Lecturer l "
            + "where lower(l.lastName) like %?1% or lower(l.firstName) like %?1% ")
    List<Lecturer> findByTemplate(String template);
}
