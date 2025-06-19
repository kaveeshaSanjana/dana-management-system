package org.myproject.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String phoneNumber;
    private String password;
}
