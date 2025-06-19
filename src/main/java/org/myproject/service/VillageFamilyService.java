package org.myproject.service;

import org.myproject.dto.VillageFamilyDTO;
import java.util.List;

public interface VillageFamilyService {
    List<VillageFamilyDTO> findAll();

    VillageFamilyDTO findById(Long villageId, Long familyId);

    VillageFamilyDTO create(VillageFamilyDTO villageFamilyDTO);

    VillageFamilyDTO update(Long villageId, Long familyId, VillageFamilyDTO villageFamilyDTO);

    void delete(Long villageId, Long familyId);

    List<VillageFamilyDTO> findByVillageId(Long villageId);

    List<VillageFamilyDTO> findByFamilyId(Long familyId);
}
