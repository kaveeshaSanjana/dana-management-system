package org.myproject.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class TempleDanaDTO {
    private Long templeId;
    private Long danaId;
    private Integer minNumberOfFamilies;
    private List<TempleDanaAssignmentDTO> assignments;
}
