package org.myproject.dto;

import lombok.Data;
import java.util.List;

@Data
public class FamilyDTO {
    private Long id;
    private String familyName;
    private String address;
    private String telephone;
    private Long userId;  // Added userId field
    private List<MemberDTO> members;
    private List<VillageFamilyDTO> villageFamilies;
}
