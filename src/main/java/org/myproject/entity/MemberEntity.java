package org.myproject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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
    private Integer nic;
    private LocalDate dob;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private FamilyEntity family;
}
