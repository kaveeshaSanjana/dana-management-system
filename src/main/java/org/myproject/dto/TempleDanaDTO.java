package org.myproject.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TempleDanaDTO {
    private Long templeId;
    private Long danaId;
    private Integer minNumberOfFamilies;
    private List<TempleDanaAssignmentDTO> assignments;
}
