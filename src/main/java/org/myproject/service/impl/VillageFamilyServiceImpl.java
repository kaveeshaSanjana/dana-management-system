package org.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myproject.dto.VillageFamilyDTO;
import org.myproject.entity.VillageFamilyEntity;
import org.myproject.entity.serializable.VillageFamilyId;
import org.myproject.entity.serializable.TempleVillageId;
import org.myproject.repository.VillageFamilyRepository;
import org.myproject.service.VillageFamilyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VillageFamilyServiceImpl implements VillageFamilyService {

    private final VillageFamilyRepository villageFamilyRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<VillageFamilyDTO> findAll() {
        return villageFamilyRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, VillageFamilyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public VillageFamilyDTO findById(Long villageId, Long familyId) {
        TempleVillageId templeVillageId = new TempleVillageId(null, villageId);
        VillageFamilyId id = new VillageFamilyId(templeVillageId, familyId);
        return villageFamilyRepository.findById(id)
                .map(entity -> modelMapper.map(entity, VillageFamilyDTO.class))
                .orElseThrow(() -> new RuntimeException("VillageFamily not found with villageId: " + villageId + " and familyId: " + familyId));
    }

    @Override
    public VillageFamilyDTO create(VillageFamilyDTO villageFamilyDTO) {
        VillageFamilyEntity entity = modelMapper.map(villageFamilyDTO, VillageFamilyEntity.class);
        TempleVillageId templeVillageId = new TempleVillageId(villageFamilyDTO.getTempleId(), villageFamilyDTO.getVillageId());
        VillageFamilyId id = new VillageFamilyId(templeVillageId, villageFamilyDTO.getFamily().getId());
        entity.setId(id);
        VillageFamilyEntity savedEntity = villageFamilyRepository.save(entity);
        return modelMapper.map(savedEntity, VillageFamilyDTO.class);
    }

    @Override
    public VillageFamilyDTO update(Long villageId, Long familyId, VillageFamilyDTO villageFamilyDTO) {
        TempleVillageId templeVillageId = new TempleVillageId(villageFamilyDTO.getTempleId(), villageId);
        VillageFamilyId id = new VillageFamilyId(templeVillageId, familyId);

        VillageFamilyEntity existingEntity = villageFamilyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VillageFamily not found with villageId: " + villageId + " and familyId: " + familyId));

        modelMapper.map(villageFamilyDTO, existingEntity);
        existingEntity.setId(id); // Preserve the ID
        VillageFamilyEntity updatedEntity = villageFamilyRepository.save(existingEntity);
        return modelMapper.map(updatedEntity, VillageFamilyDTO.class);
    }

    @Override
    public void delete(Long villageId, Long familyId) {
        TempleVillageId templeVillageId = new TempleVillageId(null, villageId);
        VillageFamilyId id = new VillageFamilyId(templeVillageId, familyId);
        if (!villageFamilyRepository.existsById(id)) {
            throw new RuntimeException("VillageFamily not found with villageId: " + villageId + " and familyId: " + familyId);
        }
        villageFamilyRepository.deleteById(id);
    }

    @Override
    public List<VillageFamilyDTO> findByVillageId(Long villageId) {
        return villageFamilyRepository.findByVillageId(villageId).stream()
                .map(entity -> modelMapper.map(entity, VillageFamilyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VillageFamilyDTO> findByFamilyId(Long familyId) {
        return villageFamilyRepository.findByFamilyId(familyId).stream()
                .map(entity -> modelMapper.map(entity, VillageFamilyDTO.class))
                .collect(Collectors.toList());
    }
}
