package org.myproject.service;

import org.myproject.dto.HeadMonkDTO;

import java.util.List;

public interface HeadMonkService {
    List<HeadMonkDTO> findAll();

    HeadMonkDTO findById(Long id);

    List<HeadMonkDTO> findByTempleId(Long templeId);

    HeadMonkDTO create(HeadMonkDTO headMonkDTO);

    HeadMonkDTO update(HeadMonkDTO headMonkDTO);

    void delete(Long id);
}
