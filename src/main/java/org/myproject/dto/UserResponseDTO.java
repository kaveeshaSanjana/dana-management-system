package org.myproject.dto;

import lombok.Data;
import lombok.Builder;
import org.myproject.enums.UserRole;

@Data
@Builder
public class UserResponseDTO {
    private String token;
    private UserRole userType;    // HEADMONK, MEMBER, or HELPER
    private String name;
    private String phoneNumber;
    private String email;
    private Long userId;
    private Long templeId;      // for HeadMonk and Helper
    private String templeName;   // for HeadMonk and Helper
}
