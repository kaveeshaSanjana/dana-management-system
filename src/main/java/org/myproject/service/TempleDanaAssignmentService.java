package org.myproject.service;

import org.myproject.dto.TempleDanaAssignmentDTO;

import java.time.LocalDate;
import java.util.List;

public interface TempleDanaAssignmentService {
    List<TempleDanaAssignmentDTO> findAll();

    TempleDanaAssignmentDTO findById(Long id);

    TempleDanaAssignmentDTO create(TempleDanaAssignmentDTO assignmentDTO);

    TempleDanaAssignmentDTO update(Long id, TempleDanaAssignmentDTO assignmentDTO);

    void delete(Long id);

    List<TempleDanaAssignmentDTO> findByTempleDanaId(Long templeId, Long danaId);

    List<TempleDanaAssignmentDTO> findByFamilyId(Long familyId);

    List<TempleDanaAssignmentDTO> findByFamilyIds(List<Long> familyIds);

    List<TempleDanaAssignmentDTO> findByDateAndTemple(Long templeId, LocalDate date);

    List<TempleDanaAssignmentDTO> findByPhoneNumber(Long templeId, String phoneNumber);

    void confirmDana(Long id);
}
