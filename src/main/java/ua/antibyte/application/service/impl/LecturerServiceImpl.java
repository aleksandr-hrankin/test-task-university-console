package ua.antibyte.application.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import ua.antibyte.application.entity.Lecturer;
import ua.antibyte.application.repository.LecturerRepository;
import ua.antibyte.application.service.LecturerService;

@Service
public class LecturerServiceImpl implements LecturerService {
    private final LecturerRepository lecturerRepository;

    public LecturerServiceImpl(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public List<Lecturer> findByTemplate(String template) {
        return lecturerRepository.findByTemplate(template.toLowerCase());
    }
}
