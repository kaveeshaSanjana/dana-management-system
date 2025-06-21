package org.myproject.service;

import org.myproject.dto.VillageDTO;
import java.util.List;

public interface VillageService {
    List<VillageDTO> findAll();

    VillageDTO findById(Long id);

    VillageDTO create(VillageDTO villageDTO);

    VillageDTO update(Long id, VillageDTO villageDTO);

    void delete(Long id);

    List<VillageDTO> findByTempleId(Long templeId);

    List<VillageDTO> findByFamilyId(Long familyId);
}
