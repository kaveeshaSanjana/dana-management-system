package org.myproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "temple_dana_assignments")
@Data
public class TempleDanaAssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "temple_id", referencedColumnName = "temple_id"),
        @JoinColumn(name = "dana_id", referencedColumnName = "dana_id")
    })
    private TempleDanaEntity templeDana;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private FamilyEntity family;

    private LocalDate date;
    private Boolean isConfirmed = false;
    private LocalDate confirmationDate;
}
