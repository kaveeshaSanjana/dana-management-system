package org.myproject.entity;

import jakarta.persistence.*;
import lombok.Data;

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

    @OneToMany(mappedBy = "temple")
    private List<TempleDanaEntity> templeDanas;

    @OneToMany(mappedBy = "temple")
    private List<HeadMonkEntity> headMonks;

    @OneToMany(mappedBy = "temple")
    private List<HelperEntity> helpers;

    @OneToMany(mappedBy = "temple")
    private List<TempleVillageEntity> templeVillages;
}
