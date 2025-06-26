package org.myproject.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class TempleDanaAssignmentRequestDTO {
    private Long id;
    private Long danaId;
    private LocalDate date;
    private Boolean isConfirmed;
    private LocalDate confirmationDate;
}
