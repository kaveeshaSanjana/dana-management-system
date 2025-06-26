package org.myproject.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VillageFamilyDTO {
    private Long villageId;
    private FamilyDTO family;
    private Long templeId;

    // Reference fields for displaying data
//    private VillageDTO village;
//    private FamilyDTO family;
//    private TempleDTO temple;
}
