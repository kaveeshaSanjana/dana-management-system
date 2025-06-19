package org.myproject.service;

import org.myproject.dto.TempleDTO;
import java.util.List;

public interface TempleService {
    List<TempleDTO> findAll();

    TempleDTO findById(Long id);

    TempleDTO create(TempleDTO templeDTO);

    TempleDTO update(Long id, TempleDTO templeDTO);

    void delete(Long id);

    List<TempleDTO> findByVillageId(Long villageId);
}
