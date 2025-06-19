package org.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myproject.dto.HelperDTO;
import org.myproject.entity.HelperEntity;
import org.myproject.repository.HelperRepository;
import org.myproject.service.HelperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HelperServiceImpl implements HelperService {

    private final HelperRepository helperRepository;
    private final ModelMapper modelMapper;

    @Override
    public HelperDTO create(HelperDTO helperDTO) {
        HelperEntity entity = modelMapper.map(helperDTO, HelperEntity.class);
        HelperEntity savedEntity = helperRepository.save(entity);
        return modelMapper.map(savedEntity, HelperDTO.class);
    }

    @Override
    public HelperDTO findById(Long id) {
        return helperRepository.findById(id)
                .map(entity -> modelMapper.map(entity, HelperDTO.class))
                .orElseThrow(() -> new RuntimeException("Helper not found with id: " + id));
    }

    @Override
    public List<HelperDTO> findAll() {
        return helperRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, HelperDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<HelperDTO> findAllByTemple(Long templeId) {
        return helperRepository.findByTempleId(templeId).stream()
                .map(entity -> modelMapper.map(entity, HelperDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public HelperDTO update(Long id, HelperDTO helperDTO) {
        HelperEntity existingEntity = helperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Helper not found with id: " + id));

        helperDTO.setId(id);
        modelMapper.map(helperDTO, existingEntity);
        HelperEntity updatedEntity = helperRepository.save(existingEntity);
        return modelMapper.map(updatedEntity, HelperDTO.class);
    }
}
