package org.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myproject.dto.MemberDTO;
import org.myproject.entity.MemberEntity;
import org.myproject.repository.MemberRepository;
import org.myproject.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, MemberDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDTO> getAllMembersByFamily(Long familyId) {
        return memberRepository.findByFamily_Id(familyId).stream()
                .map(entity -> modelMapper.map(entity, MemberDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDTO> getAllMembersByTemple(Long templeId) {
        // This method needs a correct implementation. Returning all members for now.
        return memberRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, MemberDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MemberDTO getMemberById(Long id) {
        return memberRepository.findById(id)
                .map(entity -> modelMapper.map(entity, MemberDTO.class))
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
    }

    @Override
    public List<MemberDTO> getAllMembersByVillage(Long villageId) {
        // This method needs a correct implementation. Returning all members for now.
        return memberRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, MemberDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MemberDTO createMember(MemberDTO memberDTO) {
        MemberEntity memberEntity = modelMapper.map(memberDTO, MemberEntity.class);
        MemberEntity savedEntity = memberRepository.save(memberEntity);
        return modelMapper.map(savedEntity, MemberDTO.class);
    }

    @Override
    public MemberDTO updateMember(Long id, MemberDTO memberDTO) {
        MemberEntity existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));

        modelMapper.map(memberDTO, existingMember);
        existingMember.setId(id); // Ensure ID is preserved

        MemberEntity updatedEntity = memberRepository.save(existingMember);
        return modelMapper.map(updatedEntity, MemberDTO.class);
    }

    @Override
    public void deleteMember(Long id) {

    }

    @Override
    public Optional<MemberDTO> getMemberByPhoneNumberAndTemple(String phoneNumber, Long templeId) {
        // First find member by phone number
        MemberEntity member = memberRepository.findByPhoneNumber(phoneNumber);
        if (member == null) {
            return Optional.empty();
        }

        // Check if any of member's families belong to villages of this temple
        boolean belongsToTemple = member.getFamily().stream()
            .flatMap(family -> family.getVillageFamilies().stream())
            .anyMatch(villageFamily ->
                villageFamily.getVillage().getTempleVillages().stream()
                    .anyMatch(templeVillage -> templeVillage.getTemple().getId().equals(templeId))
            );

        if (!belongsToTemple) {
            return Optional.empty();
        }

        return Optional.of(modelMapper.map(member, MemberDTO.class));
    }
}
