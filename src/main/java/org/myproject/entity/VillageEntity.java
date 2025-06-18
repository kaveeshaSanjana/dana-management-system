package org.myproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "villages")
@Data
public class VillageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String province;
    private String district;
    private String country;
    private String postalCode;

    @OneToMany(mappedBy = "village")
    private List<TempleVillageEntity> templeVillages;

    @OneToMany(mappedBy = "village")
    private List<VillageFamilyEntity> villageFamilies;
}
