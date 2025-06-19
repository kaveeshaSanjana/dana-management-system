package org.myproject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "family")
@Data
public class FamilyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String familyName;
    private String address;
    private String telephone;
    private Long userId;  // Added userId field

    @OneToMany
    private List<MemberEntity> members;

    @OneToMany(mappedBy = "family")
    private List<VillageFamilyEntity> villageFamilies;

    @OneToMany(mappedBy = "family")
    private List<TempleDanaAssignmentEntity> templeDanaAssignments;
}
