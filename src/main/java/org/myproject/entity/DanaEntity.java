package org.myproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.myproject.enums.DayTime;

import java.util.List;

@Entity
@Table(name = "dana")
@Data
public class DanaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private DayTime time;

    @OneToMany(mappedBy = "dana")
    private List<TempleDanaEntity> templeDanas;
}
