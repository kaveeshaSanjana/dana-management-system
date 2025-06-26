package org.myproject.service;

import org.myproject.dto.TempleVillageDTO;
import java.util.List;

public interface TempleVillageService {
    List<TempleVillageDTO> findAll();

    TempleVillageDTO findById(Long templeId, Long villageId);

    TempleVillageDTO create(TempleVillageDTO templeVillageDTO);

    TempleVillageDTO update(Long templeId, Long villageId, TempleVillageDTO templeVillageDTO);

    void delete(Long templeId, Long villageId);

    List<TempleVillageDTO> findByTempleId(Long templeId);

    List<TempleVillageDTO> findByVillageId(Long villageId);

    boolean isVillageAssignedToTemple(Long templeId, Long villageId);

    TempleVillageDTO assignVillageToTemple(Long templeId, Long villageId);
}
