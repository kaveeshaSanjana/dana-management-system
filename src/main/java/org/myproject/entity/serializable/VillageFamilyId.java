package org.myproject.entity.serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VillageFamilyId implements Serializable {
    private TempleVillageId templeVillageId;
    private Long familyId;
}
