package org.myproject.service;

import org.myproject.dto.FamilyDTO;

import java.util.List;

public interface FamilyService {
    List<FamilyDTO> getAllFamilies();

    List<FamilyDTO> getFamiliesByTempleId(Long templeId);

    FamilyDTO getFamilyById(Long id);

    List<FamilyDTO> getFamiliesByVillageId(Long villageId);

    FamilyDTO createFamily(FamilyDTO familyDTO);

    FamilyDTO updateFamily(FamilyDTO familyDTO);

    void deleteFamily(Long id);

    List<FamilyDTO> getAllFamiliesByUserId(Long userId);
}
