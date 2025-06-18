package org.myproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.myproject.entity.serializable.TempleDanaEntityId;

import java.util.List;

@Entity
@Table(name = "temple_dana")
@Data
public class TempleDanaEntity {

    @EmbeddedId
    private TempleDanaEntityId id;

    @MapsId("templeId")
    @ManyToOne
    @JoinColumn(name = "temple_id")
    private TempleEntity temple;

    @MapsId("danaId")
    @ManyToOne
    @JoinColumn(name = "dana_id")
    private DanaEntity dana;

    private Integer minNumberOfFamilies;

    @OneToMany(mappedBy = "templeDana", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TempleDanaAssignmentEntity> assignments;
}
