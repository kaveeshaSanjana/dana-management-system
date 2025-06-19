package org.myproject.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDTO {
    private Long id;
    private String name;
    private String nic;
    private LocalDate dob;
    private String address;
    private String email;
    private String phoneNumber;
}
