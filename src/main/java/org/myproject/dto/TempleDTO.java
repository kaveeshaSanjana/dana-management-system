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
    private List<TempleDanaDTO> templeDanas;
    private List<HeadMonkDTO> headMonks;
    private List<HelperDTO> helpers;
    private List<TempleVillageDTO> templeVillages;
}
