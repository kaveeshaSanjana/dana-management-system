package org.myproject.dto;

import lombok.Data;
import org.myproject.enums.Province;
import org.myproject.enums.District;
import org.myproject.enums.Town;
import java.util.List;

@Data
public class VillageDTO {
    private Long id;
    private String name;
    private Province province;
    private District district;
    private Town town;
    private String country;
    private String postalCode;
}
