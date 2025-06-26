package org.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myproject.dto.TempleDanaAssignmentDTO;
import org.myproject.dto.TempleDTO;
import org.myproject.entity.TempleDanaAssignmentEntity;
import org.myproject.entity.serializable.TempleDanaEntityId;
import org.myproject.repository.TempleDanaAssignmentRepository;
import org.myproject.repository.TempleRepository;
import org.myproject.service.TempleDanaAssignmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TempleDanaAssignmentServiceImpl implements TempleDanaAssignmentService {

    private final TempleDanaAssignmentRepository assignmentRepository;
    private final ModelMapper modelMapper;
    private final TempleRepository templeRepository;

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
        // Validate temple dana reference
        if (assignmentDTO.getTempleDana() == null || assignmentDTO.getTempleDana().getDana().getId() == null) {
            throw new RuntimeException("Temple dana reference (ID) is required");
        }

        if (assignmentDTO.getFamily() == null || assignmentDTO.getFamily().getId() == null) {
            throw new RuntimeException("Family reference is required");
        }

        // Handle the date
        if (assignmentDTO.getDate() == null) {
            assignmentDTO.setDate(LocalDate.now());
        }

        // Set default values for new assignments
        assignmentDTO.setIsConfirmed(false);
        assignmentDTO.setConfirmationDate(null);

        try {
            // Create new entity and set the required fields
            TempleDanaAssignmentEntity entity = new TempleDanaAssignmentEntity();

            // Set basic fields
            entity.setDate(assignmentDTO.getDate());
            entity.setIsConfirmed(assignmentDTO.getIsConfirmed());
            entity.setConfirmationDate(assignmentDTO.getConfirmationDate());

            // Set family reference
            entity.setFamily(modelMapper.map(assignmentDTO.getFamily(), org.myproject.entity.FamilyEntity.class));

            // Set temple dana reference - only map using the ID
            org.myproject.entity.TempleDanaEntity templeDanaEntity = new org.myproject.entity.TempleDanaEntity();
            templeDanaEntity.setId(new TempleDanaEntityId(assignmentDTO.getTempleDana().getTemple().getId(),assignmentDTO.getTempleDana().getDana().getId()));
            entity.setTempleDana(templeDanaEntity);

            TempleDanaAssignmentEntity savedEntity = assignmentRepository.save(entity);
            return modelMapper.map(savedEntity, TempleDanaAssignmentDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create temple dana assignment: " + e.getMessage());
        }
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

    @Override
    public List<TempleDanaAssignmentDTO> findByFamilyIds(List<Long> familyIds) {
        return assignmentRepository.findByFamilyIds(familyIds).stream()
                .map(entity -> {
                    TempleDanaAssignmentDTO dto = modelMapper.map(entity, TempleDanaAssignmentDTO.class);
                    if (dto.getTempleDana() != null && dto.getTempleDana().getTemple() != null) {
                        TempleDTO temple = modelMapper.map(entity.getTempleDana().getTemple(), TempleDTO.class);
                        dto.getTempleDana().setTemple(temple);
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<TempleDanaAssignmentDTO> findByDateAndTemple(Long templeId, LocalDate date) {
        return assignmentRepository.findByDateAndTemple(templeId,date).stream()
                .map(entity -> {
                    TempleDanaAssignmentDTO dto = modelMapper.map(entity, TempleDanaAssignmentDTO.class);
                    if (dto.getTempleDana() != null && dto.getTempleDana().getTemple() != null) {
                        TempleDTO temple = modelMapper.map(entity.getTempleDana().getTemple(), TempleDTO.class);
                        dto.getTempleDana().setTemple(temple);
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<TempleDanaAssignmentDTO> findByPhoneNumber(Long templeId, String phoneNumber) {
        List<TempleDanaAssignmentEntity> assignments = assignmentRepository.findByPhoneNumber(templeId, phoneNumber);
        return assignments.stream()
                .map(entity -> modelMapper.map(entity, TempleDanaAssignmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void confirmDana(Long id) {
        TempleDanaAssignmentEntity assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dana assignment not found with id: " + id));

        if (assignment.getIsConfirmed()) {
            throw new IllegalStateException("Dana is already confirmed");
        }

        assignment.setIsConfirmed(true);
        assignmentRepository.save(assignment);
    }
}
