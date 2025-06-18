package org.myproject.dto;

import lombok.Data;
import org.myproject.enums.DayTime;

@Data
public class DanaDTO {
    private Long id;
    private String name;
    private String description;
    private DayTime time;
}
