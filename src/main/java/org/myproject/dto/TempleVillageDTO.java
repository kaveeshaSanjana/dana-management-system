package org.myproject.dto;

import lombok.Data;
import java.util.List;

@Data
public class TempleVillageDTO {
    private Long templeId;
    private Long villageId;
    private List<VillageFamilyDTO> villageFamilies;
}
