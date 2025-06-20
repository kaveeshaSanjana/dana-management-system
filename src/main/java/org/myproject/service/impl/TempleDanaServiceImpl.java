package org.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myproject.dto.TempleDanaDTO;
import org.myproject.entity.TempleDanaEntity;
import org.myproject.entity.serializable.TempleDanaEntityId;
import org.myproject.repository.TempleDanaRepository;
import org.myproject.service.TempleDanaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TempleDanaServiceImpl implements TempleDanaService {

    private final TempleDanaRepository templeDanaRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TempleDanaDTO> findAll() {
        return templeDanaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, TempleDanaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TempleDanaDTO findById(Long templeId, Long danaId) {
        TempleDanaEntityId id = new TempleDanaEntityId(templeId, danaId);
        return templeDanaRepository.findById(id)
                .map(entity -> modelMapper.map(entity, TempleDanaDTO.class))
                .orElseThrow(() -> new RuntimeException("TempleDana not found with templeId: " + templeId + " and danaId: " + danaId));
    }

    @Override
    public TempleDanaDTO create(TempleDanaDTO templeDanaDTO) {
        TempleDanaEntity entity = modelMapper.map(templeDanaDTO, TempleDanaEntity.class);
        TempleDanaEntityId id = new TempleDanaEntityId(templeDanaDTO.getDana().getId(), templeDanaDTO.getDana().getId());
        entity.setId(id);
        TempleDanaEntity savedEntity = templeDanaRepository.save(entity);
        return modelMapper.map(savedEntity, TempleDanaDTO.class);
    }

    @Override
    public TempleDanaDTO update(Long templeId, Long danaId, TempleDanaDTO templeDanaDTO) {
        TempleDanaEntityId id = new TempleDanaEntityId(templeId, danaId);
        TempleDanaEntity existingEntity = templeDanaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TempleDana not found with templeId: " + templeId + " and danaId: " + danaId));

        modelMapper.map(templeDanaDTO, existingEntity);
        existingEntity.setId(id); // Ensure ID is preserved
        TempleDanaEntity updatedEntity = templeDanaRepository.save(existingEntity);
        return modelMapper.map(updatedEntity, TempleDanaDTO.class);
    }

    @Override
    public void delete(Long templeId, Long danaId) {
        TempleDanaEntityId id = new TempleDanaEntityId(templeId, danaId);
        if (!templeDanaRepository.existsById(id)) {
            throw new RuntimeException("TempleDana not found with templeId: " + templeId + " and danaId: " + danaId);
        }
        templeDanaRepository.deleteById(id);
    }

    @Override
    public List<TempleDanaDTO> findByTempleId(Long templeId) {
        return templeDanaRepository.findByTempleId(templeId).stream()
                .map(entity -> modelMapper.map(entity, TempleDanaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TempleDanaDTO> findByDanaId(Long danaId) {
        return templeDanaRepository.findByDanaId(danaId).stream()
                .map(entity -> modelMapper.map(entity, TempleDanaDTO.class))
                .collect(Collectors.toList());
    }
}
