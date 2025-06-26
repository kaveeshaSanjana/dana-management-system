package org.myproject.dto;

import lombok.Data;

@Data
public class HeadMonkDTO extends SuperUserDto {
    private Long id;
    private String monkName;
    private String email;
    private String phoneNumber;
    private String address;
    private Long templeId;  // Reference to temple
}
