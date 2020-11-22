package ua.antibyte.application.service;

import java.util.List;
import ua.antibyte.application.entity.Lecturer;

public interface LecturerService {
    List<Lecturer> findByTemplate(String template);
}
