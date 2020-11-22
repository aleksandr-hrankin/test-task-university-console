package ua.antibyte.application.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.antibyte.application.entity.Lecturer;

@Data
@AllArgsConstructor
public class StatisticsResponseDto {
    private Lecturer.Degree degree;
    private long quantity;
}
