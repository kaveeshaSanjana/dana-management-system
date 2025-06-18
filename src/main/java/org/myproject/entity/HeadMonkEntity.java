package org.myproject.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "head_monks")
@Data
public class HeadMonkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String monkName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;

    @ManyToOne
    @JoinColumn(name = "temple_id")
    private TempleEntity temple;
}
