package org.myproject.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TempleDanaAssignmentDTO {
    private Long id;
    private TempleDanaDTOForDanaAssignmentDTO templeDana;
    private FamilyDTO family;
    private LocalDate date;
    private Boolean isConfirmed;
    private LocalDate confirmationDate;
}
