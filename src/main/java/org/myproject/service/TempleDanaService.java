package org.myproject.service;

import org.myproject.dto.TempleDanaDTO;
import org.myproject.dto.TempleDanaDTOForTDC;

import java.util.List;

public interface TempleDanaService {
    List<TempleDanaDTOForTDC> findAll();

    TempleDanaDTO findById(Long templeId, Long danaId);

    TempleDanaDTO create(TempleDanaDTO templeDanaDTO);

    TempleDanaDTO update(Long templeId, Long danaId, TempleDanaDTO templeDanaDTO);

    void delete(Long templeId, Long danaId);

    List<TempleDanaDTOForTDC> findByTempleId(Long templeId);

    List<TempleDanaDTO> findByDanaId(Long danaId);

    TempleDanaDTO assignDanaToTemple(Long templeId, Long danaId, Integer minCount);
}
