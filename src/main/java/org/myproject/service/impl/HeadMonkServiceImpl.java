package org.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myproject.dto.HeadMonkDTO;
import org.myproject.entity.HeadMonkEntity;
import org.myproject.repository.HeadMonkRepository;
import org.myproject.service.HeadMonkService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeadMonkServiceImpl implements HeadMonkService {

    private final HeadMonkRepository headMonkRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<HeadMonkDTO> findAll() {
        return headMonkRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, HeadMonkDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public HeadMonkDTO findById(Long id) {
        return headMonkRepository.findById(id)
                .map(entity -> modelMapper.map(entity, HeadMonkDTO.class))
                .orElseThrow(() -> new RuntimeException("HeadMonk not found with id: " + id));
    }

    @Override
    public List<HeadMonkDTO> findByTempleId(Long templeId) {
        return headMonkRepository.findByTempleId(templeId).stream()
                .map(entity -> modelMapper.map(entity, HeadMonkDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public HeadMonkDTO create(HeadMonkDTO headMonkDTO) {
        HeadMonkEntity entity = modelMapper.map(headMonkDTO, HeadMonkEntity.class);
        HeadMonkEntity savedEntity = headMonkRepository.save(entity);
        return modelMapper.map(savedEntity, HeadMonkDTO.class);
    }

    @Override
    public HeadMonkDTO update(HeadMonkDTO headMonkDTO) {
        HeadMonkEntity existingEntity = headMonkRepository.findById(headMonkDTO.getId())
                .orElseThrow(() -> new RuntimeException("HeadMonk not found with id: " + headMonkDTO.getId()));

        modelMapper.map(headMonkDTO, existingEntity);
        HeadMonkEntity updatedEntity = headMonkRepository.save(existingEntity);
        return modelMapper.map(updatedEntity, HeadMonkDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (!headMonkRepository.existsById(id)) {
            throw new RuntimeException("HeadMonk not found with id: " + id);
        }
        headMonkRepository.deleteById(id);
    }
}
