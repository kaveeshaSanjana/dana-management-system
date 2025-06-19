package org.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myproject.dto.TempleDanaAssignmentDTO;
import org.myproject.entity.TempleDanaAssignmentEntity;
import org.myproject.repository.TempleDanaAssignmentRepository;
import org.myproject.service.TempleDanaAssignmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TempleDanaAssignmentServiceImpl implements TempleDanaAssignmentService {

    private final TempleDanaAssignmentRepository assignmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TempleDanaAssignmentDTO> findAll() {
        return assignmentRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, TempleDanaAssignmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TempleDanaAssignmentDTO findById(Long id) {
        return assignmentRepository.findById(id)
                .map(entity -> modelMapper.map(entity, TempleDanaAssignmentDTO.class))
                .orElseThrow(() -> new RuntimeException("TempleDanaAssignment not found with id: " + id));
    }

    @Override
    public TempleDanaAssignmentDTO create(TempleDanaAssignmentDTO assignmentDTO) {
        TempleDanaAssignmentEntity entity = modelMapper.map(assignmentDTO, TempleDanaAssignmentEntity.class);
        TempleDanaAssignmentEntity savedEntity = assignmentRepository.save(entity);
        return modelMapper.map(savedEntity, TempleDanaAssignmentDTO.class);
    }

    @Override
    public TempleDanaAssignmentDTO update(Long id, TempleDanaAssignmentDTO assignmentDTO) {
        TempleDanaAssignmentEntity existingEntity = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TempleDanaAssignment not found with id: " + id));

        assignmentDTO.setId(id);
        modelMapper.map(assignmentDTO, existingEntity);
        TempleDanaAssignmentEntity updatedEntity = assignmentRepository.save(existingEntity);
        return modelMapper.map(updatedEntity, TempleDanaAssignmentDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (!assignmentRepository.existsById(id)) {
            throw new RuntimeException("TempleDanaAssignment not found with id: " + id);
        }
        assignmentRepository.deleteById(id);
    }

    @Override
    public List<TempleDanaAssignmentDTO> findByTempleDanaId(Long templeId, Long danaId) {
        return assignmentRepository.findByTempleDanaTempleIdAndTempleDanaDanaId(templeId, danaId).stream()
                .map(entity -> modelMapper.map(entity, TempleDanaAssignmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TempleDanaAssignmentDTO> findByFamilyId(Long familyId) {
        return assignmentRepository.findByFamilyId(familyId).stream()
                .map(entity -> modelMapper.map(entity, TempleDanaAssignmentDTO.class))
                .collect(Collectors.toList());
    }
}
