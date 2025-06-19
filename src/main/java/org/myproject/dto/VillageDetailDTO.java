package org.myproject.dto;

import lombok.Data;

@Data
public class VillageDetailDTO {
    private Long id;
    private String name;
    private String province;
    private String district;
    private String country;
    private String postalCode;
}
