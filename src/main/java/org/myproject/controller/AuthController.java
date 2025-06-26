package org.myproject.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myproject.config.JwtUtil;
import org.myproject.dto.FamilyDTO;
import org.myproject.dto.LoginRequestDTO;
import org.myproject.dto.MemberResponseDTO;
import org.myproject.dto.UserResponseDTO;
import org.myproject.entity.HeadMonkEntity;
import org.myproject.entity.HelperEntity;
import org.myproject.entity.MemberEntity;
import org.myproject.enums.UserRole;
import org.myproject.repository.HeadMonkRepository;
import org.myproject.repository.HelperRepository;
import org.myproject.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final HeadMonkRepository headMonkRepository;
    private final MemberRepository memberRepository;
    private final HelperRepository helperRepository;
    private final ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        // Try HeadMonk
        HeadMonkEntity headMonk = headMonkRepository.findByPhoneNumber(loginRequest.getPhoneNumber());
        if (headMonk != null && passwordEncoder.matches(loginRequest.getPassword(), headMonk.getPassword())) {
            String token = jwtUtil.generateToken(headMonk.getPhoneNumber(), UserRole.HEADMONK , null,headMonk.getTemple().getId());
            return ResponseEntity.ok(UserResponseDTO.builder()
                    .token(token)
                    .userType(UserRole.HEADMONK)
                    .userId(headMonk.getId())
                    .name(headMonk.getMonkName())
                    .phoneNumber(headMonk.getPhoneNumber())
                    .email(headMonk.getEmail())
                    .templeId(headMonk.getTemple().getId())
                    .templeName(headMonk.getTemple().getName())
                    .build());
        }

        // Try Member

        MemberEntity member = memberRepository.findByPhoneNumber(loginRequest.getPhoneNumber());
        if (member != null && passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            String token = jwtUtil.generateToken(member.getPhoneNumber(), UserRole.MEMBER ,member.getFamily().stream().map(familyEntity -> familyEntity.getId()).toList(),null);
            return ResponseEntity.ok(MemberResponseDTO.builder()
                    .token(token)
                    .userType(UserRole.MEMBER)
                    .userId(member.getId())
                    .name(member.getName())
                    .phoneNumber(member.getPhoneNumber())
                    .email(member.getEmail())
                    .family(member.getFamily().stream().map(e -> modelMapper.map(e, FamilyDTO.class)).toList())
                    .build());
        }

        // Try Helper
        HelperEntity helper = helperRepository.findByPhoneNumber(loginRequest.getPhoneNumber());
        if (helper != null && passwordEncoder.matches(loginRequest.getPassword(), helper.getPassword())) {
            String token = jwtUtil.generateToken(helper.getPhoneNumber(), UserRole.HELPER,null, helper.getTemple().getId());
            return ResponseEntity.ok(UserResponseDTO.builder()
                    .token(token)
                    .userType(UserRole.HELPER)
                    .userId(helper.getId())
                    .name(helper.getName())
                    .phoneNumber(helper.getPhoneNumber())
                    .email(helper.getEmail())
                    .templeId(helper.getTemple().getId())
                    .templeName(helper.getTemple().getName())
                    .build());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid phone number or password");
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        try {
            String actualToken = token.replace("Bearer ", "");
            return ResponseEntity.ok(jwtUtil.validateToken(actualToken));
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
}
