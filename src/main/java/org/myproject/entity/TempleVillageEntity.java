package org.myproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.myproject.entity.serializable.TempleVillageId;

import java.util.List;

@Entity
@Table(name = "temple_villages")
@Data
public class TempleVillageEntity {

    @EmbeddedId
    private TempleVillageId id;

    @MapsId("templeId")
    @ManyToOne
    @JoinColumn(name = "temple_id")
    private TempleEntity temple;

    @MapsId("villageId")
    @ManyToOne
    @JoinColumn(name = "village_id")
    private VillageEntity village;

    @OneToMany(mappedBy = "templeVillage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VillageFamilyEntity> villageFamilies;
}
