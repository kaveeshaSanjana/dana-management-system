package org.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myproject.dto.TempleDanaDTO;
import org.myproject.dto.TempleDanaDTOForTDC;
import org.myproject.entity.TempleDanaEntity;
import org.myproject.entity.TempleEntity;
import org.myproject.entity.DanaEntity;
import org.myproject.entity.serializable.TempleDanaEntityId;
import org.myproject.repository.TempleDanaRepository;
import org.myproject.repository.TempleRepository;
import org.myproject.repository.DanaRepository;
import org.myproject.service.TempleDanaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TempleDanaServiceImpl implements TempleDanaService {

    private final TempleDanaRepository templeDanaRepository;
    private final ModelMapper modelMapper;
    private final TempleRepository templeRepository;
    private final DanaRepository danaRepository;

    @Override
    public List<TempleDanaDTOForTDC> findAll() {
        return templeDanaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, TempleDanaDTOForTDC.class))
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
        TempleDanaEntityId id = new TempleDanaEntityId(
            templeDanaDTO.getTemple().getId(),
            templeDanaDTO.getDana().getId()
        );
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
    public List<TempleDanaDTOForTDC> findByTempleId(Long templeId) {
        return templeDanaRepository.findByTempleId(templeId).stream()
                .map(entity -> modelMapper.map(entity, TempleDanaDTOForTDC.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TempleDanaDTO> findByDanaId(Long danaId) {
        return templeDanaRepository.findByDanaId(danaId).stream()
                .map(entity -> modelMapper.map(entity, TempleDanaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TempleDanaDTO assignDanaToTemple(Long templeId, Long danaId, Integer minCount) {
        // Validate temple and dana exist
        TempleEntity temple = templeRepository.findById(templeId)
                .orElseThrow(() -> new RuntimeException("Temple not found with id: " + templeId));
        DanaEntity dana = danaRepository.findById(danaId)
                .orElseThrow(() -> new RuntimeException("Dana not found with id: " + danaId));

        // Check if association already exists
        TempleDanaEntityId id = new TempleDanaEntityId(templeId, danaId);
        if (templeDanaRepository.existsById(id)) {
            throw new RuntimeException("Temple-Dana association already exists");
        }

        // Create a new TempleDanaEntity
        TempleDanaEntity entity = new TempleDanaEntity();
        entity.setId(id);
        entity.setTemple(temple);
        entity.setDana(dana);
        entity.setMinNumberOfFamilies(minCount);

        // Save the entity
        TempleDanaEntity savedEntity = templeDanaRepository.save(entity);

        // Map to DTO and return
        return modelMapper.map(savedEntity, TempleDanaDTO.class);
    }
}
