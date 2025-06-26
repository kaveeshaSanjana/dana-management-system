package org.myproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.myproject.enums.Province;
import org.myproject.enums.District;
import org.myproject.enums.Town;
import java.util.List;

@Entity
@Table(name = "villages")
@Data
public class VillageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Province province;

    @Enumerated(EnumType.STRING)
    private District district;

    @Enumerated(EnumType.STRING)
    private Town town;

    private String country;
    private String postalCode;

    @OneToMany(mappedBy = "village")
    private List<TempleVillageEntity> templeVillages;

    @OneToMany(mappedBy = "village")
    private List<VillageFamilyEntity> villageFamilies;
}
