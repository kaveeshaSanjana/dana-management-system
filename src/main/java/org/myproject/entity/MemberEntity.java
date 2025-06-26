package org.myproject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "member")
@Data
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String nic;
    private LocalDate dob;

    @ManyToMany
    @JoinTable(
            name = "member_family",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "family_id")
    )
    private List<FamilyEntity> family;
}
