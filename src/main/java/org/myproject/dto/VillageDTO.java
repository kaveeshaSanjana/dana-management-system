package org.myproject.dto;

import lombok.Data;
import java.util.List;

@Data
public class VillageDTO {
    private Long id;
    private String name;
    private String province;
    private String district;
    private String country;
    private String postalCode;
    private List<TempleVillageDTO> templeVillages;
    private List<VillageFamilyDTO> villageFamilies;
}
