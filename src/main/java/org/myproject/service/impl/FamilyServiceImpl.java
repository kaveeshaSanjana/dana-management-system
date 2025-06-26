package org.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myproject.dto.*;
import org.myproject.entity.*;
import org.myproject.entity.serializable.TempleVillageId;
import org.myproject.entity.serializable.VillageFamilyId;
import org.myproject.repository.*;
import org.myproject.service.FamilyService;
import org.myproject.service.TempleDanaAssignmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;
    private final MemberRepository memberRepository;
    private final TempleVillageRepository templeVillageRepository;
    private final VillageFamilyRepository villageFamilyRepository;
    private final TempleDanaAssignmentService templeDanaAssignmentService;
    private final ModelMapper modelMapper;
    private final  TempleDanaAssignmentRepository templeDanaAssignmentRepository;

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

    @Override
    public void assignMembersToFamily(MemberFamilyAssignmentDTO assignmentDTO) {
        FamilyEntity family = familyRepository.findById(assignmentDTO.getFamilyId())
                .orElseThrow(() -> new RuntimeException("Family not found with id: " + assignmentDTO.getFamilyId()));

        List<MemberEntity> members = memberRepository.findAllById(assignmentDTO.getMemberIds());
        if (members.size() != assignmentDTO.getMemberIds().size()) {
            throw new RuntimeException("Some members were not found");
        }

        for (MemberEntity member : members) {
            if (!member.getFamily().contains(family)) {
                member.getFamily().add(family);
            }
        }

        memberRepository.saveAll(members);
    }

    @Override
    public FamilyDTO assignMembersToFamilyAtOnce(Long templeId, Long villageId,
                                                FamilyDTO familyDTO, List<MemberDTO> memberDTOs,
                                                List<TempleDanaAssignmentRequestDTO> danaAssignments) {
        // Verify temple-village relationship
        TempleVillageId templeVillageId = new TempleVillageId(templeId, villageId);
        templeVillageRepository.findById(templeVillageId)
                .orElseThrow(() -> new RuntimeException("Temple-Village relationship not found"));

        // Create and save the family
        FamilyEntity savedFamily;
        FamilyEntity familyEntity;
        try {
            familyEntity = modelMapper.map(familyDTO, FamilyEntity.class);
            savedFamily = familyRepository.save(familyEntity);
        System.err.println("Family created successfully with ID: " + savedFamily.getId());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create family: " + e.getMessage());
        }

        // Create village-family relationship
        try {
            villageFamilyRepository.assignFamilyToVillageManually(templeId, villageId, familyEntity.getId());
        System.err.println("Family assigned to village successfully");
        }catch (Exception e) {
            throw new RuntimeException("Failed to assign family to village: " + e.getMessage());
        }
        // Process and save members
        List<MemberEntity> memberEntities = memberDTOs.stream()
                .map(memberDTO -> {
                    // Check if member already exists through mobile number
                    MemberEntity existingMember = memberRepository.findByPhoneNumber(memberDTO.getPhoneNumber());
                    if (existingMember != null) {
                        System.err.println("Found existing member with phone number: " + memberDTO.getPhoneNumber());
                        // Add the new family to existing member's families
                        List<FamilyEntity> existingFamilies = existingMember.getFamily();
                        if (!existingFamilies.contains(savedFamily)) {
                            existingFamilies.add(savedFamily);
                            existingMember.setFamily(existingFamilies);
                        }
                        return existingMember;
                    }
                    // If member doesn't exist, create new
                    MemberEntity memberEntity = modelMapper.map(memberDTO, MemberEntity.class);
                    memberEntity.setFamily(List.of(savedFamily));
                    return memberEntity;
                })
                .collect(Collectors.toList());
        memberRepository.saveAll(memberEntities);
        System.err.println("Members assigned to family successfully");
        // Process dana assignments if any
        if (danaAssignments != null && !danaAssignments.isEmpty()) {
            try {
                danaAssignments.forEach(requestDTO -> {
                    System.err.println("Processing dana assignment for family: " + savedFamily.getId());
                    templeDanaAssignmentRepository.assignDana(
                        templeId,
                        requestDTO.getDanaId(),
                        savedFamily.getId(),
                        requestDTO.getDate()
                    );
                    System.err.println("Dana assignment created successfully");
                });
            } catch (Exception e) {
                throw new RuntimeException("Failed to assign dana: " + e.getMessage());
            }
        }
        System.err.println("Dana assignments processed successfully");
        return modelMapper.map(savedFamily, FamilyDTO.class);
    }

    @Override
    @Transactional
    public FamilyDTO assignMembersToFamilyAtOnce(Long templeId, Long villageId,
            FamilyDTO familyDTO, List<MemberDTO> memberDTOs) {
        return assignMembersToFamilyAtOnce(templeId, villageId, familyDTO, memberDTOs, List.of());
    }
}
