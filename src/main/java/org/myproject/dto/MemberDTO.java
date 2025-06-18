package org.myproject.dto;

import lombok.Data;

@Data
public class MemberDTO {
    private Long id;
    private String name;
    private String nic;
    private String contactNumber;
    private Long familyId;  // Reference to family
}
