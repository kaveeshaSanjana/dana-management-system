package org.myproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.myproject.entity.serializable.VillageFamilyId;

@Entity
@Table(name = "village_family")
@Data
public class VillageFamilyEntity {

    @EmbeddedId
    private VillageFamilyId id;

    @MapsId("templeVillageId")
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "temple_id", referencedColumnName = "temple_id"),
        @JoinColumn(name = "village_id", referencedColumnName = "village_id")
    })
    private TempleVillageEntity templeVillage;

    @MapsId("familyId")
    @ManyToOne
    @JoinColumn(name = "family_id")
    private FamilyEntity family;

    @ManyToOne
    @JoinColumn(name = "village_id", insertable = false, updatable = false)
    private VillageEntity village;
}
