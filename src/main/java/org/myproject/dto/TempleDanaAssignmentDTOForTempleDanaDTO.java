package org.myproject.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class TempleDanaAssignmentDTOForTempleDanaDTO {
    private Long id;
    private FamilyDTO family;
    private LocalDate date;
    private Boolean isConfirmed;
    private LocalDate confirmationDate;
}
