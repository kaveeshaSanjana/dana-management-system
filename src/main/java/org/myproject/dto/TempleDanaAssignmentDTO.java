package org.myproject.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TempleDanaAssignmentDTO {
    private Long id;
    private Long templeDanaId;
    private Long familyId;
    private LocalDate date;
    private Boolean isConfirmed;
    private LocalDate confirmationDate;
}
