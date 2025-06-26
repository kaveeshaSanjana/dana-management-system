package org.myproject.service;

import org.myproject.dto.VillageDTO;
import org.myproject.enums.Province;
import org.myproject.enums.District;
import org.myproject.enums.Town;
import java.util.List;

public interface VillageService {
    List<VillageDTO> findAll();

    VillageDTO findById(Long id);

    VillageDTO create(VillageDTO villageDTO);

    VillageDTO update(Long id, VillageDTO villageDTO);

    void delete(Long id);

    List<VillageDTO> findByTempleId(Long templeId);

    List<VillageDTO> findByFamilyId(Long familyId);

    List<VillageDTO> findByFilters(Province province, District district, Town town);
}
