package org.myproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TempleVillageCreateRequestDTO {
    @NotNull(message = "Village ID cannot be null")
    private Long villageId;
}
