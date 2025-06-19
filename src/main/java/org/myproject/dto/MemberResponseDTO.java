package org.myproject.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.usertype.UserType;
import org.myproject.enums.UserRole;

import java.util.List;
@Data
@Builder
public class MemberResponseDTO {
    private String token;
    private UserRole userType;    // HEADMONK, MEMBER, or HELPER
    private String name;
    private String phoneNumber;
    private String email;
    private Long userId;
    private List<FamilyDTO> family;
}
