package org.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myproject.dto.TempleDTO;
import org.myproject.entity.TempleEntity;
import org.myproject.enums.District;
import org.myproject.enums.Province;
import org.myproject.enums.Town;
import org.myproject.repository.TempleRepository;
import org.myproject.service.TempleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TempleServiceImpl implements TempleService {

    private final TempleRepository templeRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TempleDTO> findAll() {
        return templeRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, TempleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TempleDTO findById(Long id) {
        return templeRepository.findById(id)
                .map(entity -> modelMapper.map(entity, TempleDTO.class))
                .orElseThrow(() -> new RuntimeException("Temple not found with id: " + id));
    }

    @Override
    public TempleDTO create(TempleDTO templeDTO) {
        TempleEntity entity = modelMapper.map(templeDTO, TempleEntity.class);
        TempleEntity savedEntity = templeRepository.save(entity);
        return modelMapper.map(savedEntity, TempleDTO.class);
    }

    @Override
    public TempleDTO update(Long id, TempleDTO templeDTO) {
        TempleEntity existingEntity = templeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Temple not found with id: " + id));

        templeDTO.setId(id);
        modelMapper.map(templeDTO, existingEntity);
        TempleEntity updatedEntity = templeRepository.save(existingEntity);
        return modelMapper.map(updatedEntity, TempleDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (!templeRepository.existsById(id)) {
            throw new RuntimeException("Temple not found with id: " + id);
        }
        templeRepository.deleteById(id);
    }

    @Override
    public List<TempleDTO> findByVillageId(Long villageId) {
        return templeRepository.findByTempleVillagesVillageId(villageId).stream()
                .map(entity -> modelMapper.map(entity, TempleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TempleDTO> findByFilters(Province province, District district, Town town) {
        return templeRepository.findByFilters(province, district, town)
                .stream()
                .map(entity -> modelMapper.map(entity, TempleDTO.class))
                .collect(Collectors.toList());
    }
}
