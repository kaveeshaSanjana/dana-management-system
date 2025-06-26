package org.myproject.dto;

import lombok.Data;
import org.myproject.enums.Province;
import org.myproject.enums.District;
import org.myproject.enums.Town;

@Data
public class TempleDTO {
    private Long id;
    private String name;
    private String address;
    private String contactNumber;
    private String email;
    private String website;
    private Province province;
    private District district;
    private Town town;
}
