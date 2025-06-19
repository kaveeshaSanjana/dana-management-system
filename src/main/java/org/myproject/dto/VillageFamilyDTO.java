package org.myproject.dto;

import lombok.Data;

@Data
public class VillageFamilyDTO {
    private Long villageId;
    private FamilyDTO family;
    private Long templeId;

    // Reference fields for displaying data
//    private VillageDTO village;
//    private FamilyDTO family;
//    private TempleDTO temple;
}
