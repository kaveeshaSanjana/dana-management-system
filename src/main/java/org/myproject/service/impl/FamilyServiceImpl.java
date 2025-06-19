package org.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myproject.dto.FamilyDTO;
import org.myproject.entity.FamilyEntity;
import org.myproject.repository.FamilyRepository;
import org.myproject.service.FamilyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<FamilyDTO> getAllFamilies() {
        return familyRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, FamilyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FamilyDTO> getFamiliesByTempleId(Long templeId) {
        return familyRepository.findByTempleDanaAssignments_TempleDana_Temple_Id(templeId).stream()
                .map(entity -> modelMapper.map(entity, FamilyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FamilyDTO getFamilyById(Long id) {
        return familyRepository.findById(id)
                .map(entity -> modelMapper.map(entity, FamilyDTO.class))
                .orElseThrow(() -> new RuntimeException("Family not found with id: " + id));
    }

    @Override
    public List<FamilyDTO> getFamiliesByVillageId(Long villageId) {
        return familyRepository.findByVillageFamilies_Id_TempleVillageId_VillageId(villageId).stream()
                .map(entity -> modelMapper.map(entity, FamilyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FamilyDTO createFamily(FamilyDTO familyDTO) {
        FamilyEntity familyEntity = modelMapper.map(familyDTO, FamilyEntity.class);
        FamilyEntity savedEntity = familyRepository.save(familyEntity);
        return modelMapper.map(savedEntity, FamilyDTO.class);
    }

    @Override
    public FamilyDTO updateFamily(FamilyDTO familyDTO) {
        FamilyEntity existingFamily = familyRepository.findById(familyDTO.getId())
                .orElseThrow(() -> new RuntimeException("Family not found with id: " + familyDTO.getId()));

        modelMapper.map(familyDTO, existingFamily);
        FamilyEntity updatedEntity = familyRepository.save(existingFamily);
        return modelMapper.map(updatedEntity, FamilyDTO.class);
    }

    @Override
    public void deleteFamily(Long id) {
        familyRepository.deleteById(id);
    }


}
