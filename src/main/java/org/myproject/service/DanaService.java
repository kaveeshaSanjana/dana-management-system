package org.myproject.service;

import org.myproject.dto.DanaDTO;
import java.util.List;

public interface DanaService {
    List<DanaDTO> findAll();

    DanaDTO findById(Long id);

    DanaDTO create(DanaDTO danaDTO);

    DanaDTO update(Long id, DanaDTO danaDTO);

    void delete(Long id);

    List<DanaDTO> findByTempleDanasTempleId(Long templeId);
}
