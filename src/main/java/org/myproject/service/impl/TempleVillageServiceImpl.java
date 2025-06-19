package org.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myproject.dto.TempleVillageDTO;
import org.myproject.entity.TempleVillageEntity;
import org.myproject.entity.serializable.TempleVillageId;
import org.myproject.repository.TempleVillageRepository;
import org.myproject.service.TempleVillageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TempleVillageServiceImpl implements TempleVillageService {

    private final TempleVillageRepository templeVillageRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TempleVillageDTO> findAll() {
        return templeVillageRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, TempleVillageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TempleVillageDTO findById(Long templeId, Long villageId) {
        TempleVillageId id = new TempleVillageId(templeId, villageId);
        return templeVillageRepository.findById(id)
                .map(entity -> modelMapper.map(entity, TempleVillageDTO.class))
                .orElseThrow(() -> new RuntimeException("TempleVillage not found with templeId: " + templeId + " and villageId: " + villageId));
    }

    @Override
    public TempleVillageDTO create(TempleVillageDTO templeVillageDTO) {
        TempleVillageEntity entity = modelMapper.map(templeVillageDTO, TempleVillageEntity.class);
        TempleVillageId id = new TempleVillageId(templeVillageDTO.getTempleId(), templeVillageDTO.getVillage().getId());
        entity.setId(id);
        TempleVillageEntity savedEntity = templeVillageRepository.save(entity);
        return modelMapper.map(savedEntity, TempleVillageDTO.class);
    }

    @Override
    public TempleVillageDTO update(Long templeId, Long villageId, TempleVillageDTO templeVillageDTO) {
        TempleVillageId id = new TempleVillageId(templeId, villageId);
        TempleVillageEntity existingEntity = templeVillageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TempleVillage not found with templeId: " + templeId + " and villageId: " + villageId));

        modelMapper.map(templeVillageDTO, existingEntity);
        existingEntity.setId(id); // Ensure ID is preserved
        TempleVillageEntity updatedEntity = templeVillageRepository.save(existingEntity);
        return modelMapper.map(updatedEntity, TempleVillageDTO.class);
    }

    @Override
    public void delete(Long templeId, Long villageId) {
        TempleVillageId id = new TempleVillageId(templeId, villageId);
        if (!templeVillageRepository.existsById(id)) {
            throw new RuntimeException("TempleVillage not found with templeId: " + templeId + " and villageId: " + villageId);
        }
        templeVillageRepository.deleteById(id);
    }

    @Override
    public List<TempleVillageDTO> findByTempleId(Long templeId) {
        return templeVillageRepository.findByTempleId(templeId).stream()
                .map(entity -> modelMapper.map(entity, TempleVillageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TempleVillageDTO> findByVillageId(Long villageId) {
        return templeVillageRepository.findByVillageId(villageId).stream()
                .map(entity -> modelMapper.map(entity, TempleVillageDTO.class))
                .collect(Collectors.toList());
    }
}
