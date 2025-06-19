package org.myproject.service;

import org.myproject.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    List<MemberDTO> getAllMembers();

    List<MemberDTO> getAllMembersByFamily(Long familyId);

    List<MemberDTO> getAllMembersByTemple(Long templeId);

    MemberDTO getMemberById(Long id);

    List<MemberDTO> getAllMembersByVillage(Long villageId);

    MemberDTO createMember(MemberDTO memberDTO);

    MemberDTO updateMember(Long id, MemberDTO memberDTO);

    void deleteMember(Long id);
}
