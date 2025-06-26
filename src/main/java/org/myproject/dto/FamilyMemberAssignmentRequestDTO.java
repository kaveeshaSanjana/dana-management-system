package org.myproject.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
public class FamilyMemberAssignmentRequestDTO {
    @NotNull(message = "Family details are required")
    @Valid
    private FamilyDTO family;

    @NotNull(message = "Members list is required")
    @Size(min = 1, message = "At least one member is required")
    @Valid
    private List<MemberDTO> members;

    @NotNull(message = "Village ID is required")
    private Long villageId;

    @Valid
    private List<TempleDanaAssignmentRequestDTO> danaAssignments; // Added Dana assignments
}
