package org.myproject.dto;

import lombok.Data;
import java.util.List;

@Data
public class TempleVillageDTO {
    private Long templeId;
    private VillageDetailDTO village;
    private List<VillageFamilyDTO> villageFamilies;
}
