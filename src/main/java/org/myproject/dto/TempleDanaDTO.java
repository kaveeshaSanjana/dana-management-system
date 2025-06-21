package org.myproject.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class TempleDanaDTO {
    private TempleDTO templeId;
    private DanaDTO dana;
    private Integer minNumberOfFamilies;
    private List<TempleDanaAssignmentDTOForTempleDanaDTO> assignments;
}

