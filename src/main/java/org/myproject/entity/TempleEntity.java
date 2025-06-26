package org.myproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.myproject.enums.District;
import org.myproject.enums.Province;
import org.myproject.enums.Town;

import java.util.List;

@Entity
@Table(name = "temple")
@Data
public class TempleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String contactNumber;
    private String email;
    private String website;

    @Enumerated(EnumType.STRING)
    private Province province;

    @Enumerated(EnumType.STRING)
    private District district;

    @Enumerated(EnumType.STRING)
    private Town town;

    @OneToMany(mappedBy = "temple")
    private List<TempleDanaEntity> templeDanas;

    @OneToMany(mappedBy = "temple")
    private List<HeadMonkEntity> headMonks;

    @OneToMany(mappedBy = "temple")
    private List<HelperEntity> helpers;

    @OneToMany(mappedBy = "temple")
    private List<TempleVillageEntity> templeVillages;
}
