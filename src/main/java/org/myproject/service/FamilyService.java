package org.myproject.service;

import org.myproject.dto.*;

import java.util.List;

public interface FamilyService {
    List<FamilyDTO> getAllFamilies();

    List<FamilyDTO> getFamiliesByTempleId(Long templeId);

    FamilyDTO getFamilyById(Long id);

    List<FamilyDTO> getFamiliesByVillageId(Long villageId);

    FamilyDTO createFamily(FamilyDTO familyDTO);

    FamilyDTO updateFamily(FamilyDTO familyDTO);

    void deleteFamily(Long id);

    void assignMembersToFamily(MemberFamilyAssignmentDTO assignmentDTO);

    FamilyDTO assignMembersToFamilyAtOnce(Long templeId, Long villageId, FamilyDTO familyDTO, List<MemberDTO> memberDTOs);

    FamilyDTO assignMembersToFamilyAtOnce(Long templeId, Long villageId,
                                          FamilyDTO familyDTO, List<MemberDTO> memberDTOs,
                                          List<TempleDanaAssignmentRequestDTO> danaAssignments);
}
