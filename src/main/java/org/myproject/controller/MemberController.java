package org.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.config.JwtUtil;
import org.myproject.dto.MemberDTO;
import org.myproject.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDTO> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/by-family/{familyId}")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDTO> getAllMembersByFamily(@PathVariable Long familyId) {
        return memberService.getAllMembersByFamily(familyId);
    }

    @GetMapping("/by-temple/{templeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDTO> getAllMembersByTemple(@PathVariable Long templeId) {
        return memberService.getAllMembersByTemple(templeId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberDTO getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping("/by-village/{villageId}")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDTO> getAllMembersByVillage(@PathVariable Long villageId) {
        return memberService.getAllMembersByVillage(villageId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO createMember(@Valid @RequestBody MemberDTO memberDTO) {
        return memberService.createMember(memberDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberDTO updateMember(@PathVariable Long id, @Valid @RequestBody MemberDTO memberDTO) {
        return memberService.updateMember(id, memberDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }

    @GetMapping("/by-phone/{phoneNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MemberDTO> getMemberByPhoneNumber(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String phoneNumber) {
        String token = authHeader.substring(7); // Remove "Bearer " prefix
        Long templeId = jwtUtil.extractTempleId(token);

        return memberService.getMemberByPhoneNumberAndTemple(phoneNumber, templeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
