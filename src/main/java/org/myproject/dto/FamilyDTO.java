package org.myproject.dto;

import lombok.Data;
import java.util.List;

@Data
public class FamilyDTO {
    private Long id;
    private String familyName;
    private String address;
    private String telephone;
}
