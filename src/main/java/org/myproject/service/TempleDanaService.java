package org.myproject.service;

import org.myproject.dto.TempleDanaDTO;
import java.util.List;

public interface TempleDanaService {
    List<TempleDanaDTO> findAll();

    TempleDanaDTO findById(Long templeId, Long danaId);

    TempleDanaDTO create(TempleDanaDTO templeDanaDTO);

    TempleDanaDTO update(Long templeId, Long danaId, TempleDanaDTO templeDanaDTO);

    void delete(Long templeId, Long danaId);

    List<TempleDanaDTO> findByTempleId(Long templeId);

    List<TempleDanaDTO> findByDanaId(Long danaId);
}
