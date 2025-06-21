package org.myproject.service;

import org.myproject.dto.VillageDTO;
import org.myproject.dto.VillageDTOForList;

import java.util.List;

public interface VillageService {
    List<VillageDTOForList> findAll();

    VillageDTO findById(Long id);

    VillageDTO create(VillageDTO villageDTO);

    VillageDTO update(Long id, VillageDTO villageDTO);

    void delete(Long id);

    List<VillageDTOForList> findByTempleId(Long templeId);

    List<VillageDTO> findByFamilyId(Long familyId);
}
