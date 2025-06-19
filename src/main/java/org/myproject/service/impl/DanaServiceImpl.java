package org.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myproject.dto.DanaDTO;
import org.myproject.entity.DanaEntity;
import org.myproject.repository.DanaRepository;
import org.myproject.service.DanaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DanaServiceImpl implements DanaService {

    private final DanaRepository danaRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<DanaDTO> findAll() {
        return danaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, DanaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DanaDTO findById(Long id) {
        return danaRepository.findById(id)
                .map(entity -> modelMapper.map(entity, DanaDTO.class))
                .orElseThrow(() -> new RuntimeException("Dana not found with id: " + id));
    }

    @Override
    public DanaDTO create(DanaDTO danaDTO) {
        DanaEntity entity = modelMapper.map(danaDTO, DanaEntity.class);
        DanaEntity savedEntity = danaRepository.save(entity);
        return modelMapper.map(savedEntity, DanaDTO.class);
    }

    @Override
    public DanaDTO update(Long id, DanaDTO danaDTO) {
        DanaEntity existingEntity = danaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dana not found with id: " + id));

        danaDTO.setId(id);
        modelMapper.map(danaDTO, existingEntity);
        DanaEntity updatedEntity = danaRepository.save(existingEntity);
        return modelMapper.map(updatedEntity, DanaDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (!danaRepository.existsById(id)) {
            throw new RuntimeException("Dana not found with id: " + id);
        }
        danaRepository.deleteById(id);
    }

    @Override
    public List<DanaDTO> findByTempleDanasTempleId(Long templeId) {
        return danaRepository.findByTempleDanasTempleId(templeId).stream()
                .map(entity -> modelMapper.map(entity, DanaDTO.class))
                .collect(Collectors.toList());
    }
}
