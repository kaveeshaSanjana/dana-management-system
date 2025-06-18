package org.myproject.service;

import org.myproject.dto.HelperDTO;

import java.util.List;

public interface HelperService {
    HelperDTO create(HelperDTO helperDTO);

    HelperDTO findById(Long id);

    List<HelperDTO> findAll();

    List<HelperDTO> findAllByTemple(Long id);

    HelperDTO update(Long id, HelperDTO helperDTO);
}
