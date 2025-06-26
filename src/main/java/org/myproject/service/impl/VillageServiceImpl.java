package org.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myproject.dto.VillageDTO;
import org.myproject.entity.VillageEntity;
import org.myproject.enums.District;
import org.myproject.enums.Province;
import org.myproject.enums.Town;
import org.myproject.repository.VillageRepository;
import org.myproject.service.VillageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VillageServiceImpl implements VillageService {

    private final VillageRepository villageRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<VillageDTO> findAll() {
        return villageRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, VillageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public VillageDTO findById(Long id) {
        return villageRepository.findById(id)
                .map(entity -> modelMapper.map(entity, VillageDTO.class))
                .orElseThrow(() -> new RuntimeException("Village not found with id: " + id));
    }

    @Override
    public VillageDTO create(VillageDTO villageDTO) {
        VillageEntity entity = modelMapper.map(villageDTO, VillageEntity.class);
        VillageEntity savedEntity = villageRepository.save(entity);
        return modelMapper.map(savedEntity, VillageDTO.class);
    }

    @Override
    public VillageDTO update(Long id, VillageDTO villageDTO) {
        VillageEntity existingEntity = villageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Village not found with id: " + id));

        villageDTO.setId(id);
        modelMapper.map(villageDTO, existingEntity);
        VillageEntity updatedEntity = villageRepository.save(existingEntity);
        return modelMapper.map(updatedEntity, VillageDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (!villageRepository.existsById(id)) {
            throw new RuntimeException("Village not found with id: " + id);
        }
        villageRepository.deleteById(id);
    }

    @Override
    public List<VillageDTO> findByTempleId(Long templeId) {
        return villageRepository.findByTempleVillagesTempleId(templeId).stream()
                .map(entity -> modelMapper.map(entity, VillageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VillageDTO> findByFamilyId(Long familyId) {
        return villageRepository.findByVillageFamiliesFamilyId(familyId).stream()
                .map(entity -> modelMapper.map(entity, VillageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VillageDTO> findByFilters(Province province, District district, Town town) {
        List<VillageEntity> villages = villageRepository.findByFilters(province, district, town);
        return villages.stream()
                .map(entity -> modelMapper.map(entity, VillageDTO.class))
                .collect(Collectors.toList());
    }
}
