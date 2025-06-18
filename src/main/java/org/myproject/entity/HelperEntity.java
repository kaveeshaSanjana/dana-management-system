package org.myproject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "helper")
@Data
public class HelperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;
    private String password;

    @ManyToOne
    @JoinColumn(name = "temple_id")
    private TempleEntity temple;
}
