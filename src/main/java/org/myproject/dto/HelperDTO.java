package org.myproject.dto;

import lombok.Data;

@Data
public class HelperDTO extends SuperUserDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private Long templeId;  // Reference to temple
}
