package org.myproject.dto;

import lombok.Data;
import java.util.List;

@Data
public class TempleDTO {
    private Long id;
    private String name;
    private String address;
    private String contactNumber;
    private String email;
    private String website;
}
