package org.myproject.dto;

import lombok.Data;
import java.util.List;

@Data
public class MemberFamilyAssignmentDTO {
    private List<Long> memberIds;
    private Long familyId;
}
