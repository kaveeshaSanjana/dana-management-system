package org.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.config.JwtUtil;
import org.myproject.dto.FamilyDTO;
import org.myproject.dto.MemberFamilyAssignmentDTO;
import org.myproject.dto.FamilyMemberAssignmentRequestDTO;
import org.myproject.service.FamilyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/family")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FamilyController {

    private final FamilyService familyService;
    private final JwtUtil jwtUtil;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FamilyDTO> getAllFamilies() {
        return familyService.getAllFamilies();
    }



    @GetMapping("/by-temple/{templeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FamilyDTO> getFamiliesByTempleId(@PathVariable Long templeId) {
        return familyService.getFamiliesByTempleId(templeId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FamilyDTO getFamilyById(@PathVariable Long id) {
        return familyService.getFamilyById(id);
    }

    @GetMapping("/by-village/{villageId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FamilyDTO> getFamiliesByVillageId(@PathVariable Long villageId) {
        return familyService.getFamiliesByVillageId(villageId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FamilyDTO createFamily(@Valid @RequestBody FamilyDTO familyDTO) {
        return familyService.createFamily(familyDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FamilyDTO updateFamily(@PathVariable Long id, @Valid @RequestBody FamilyDTO familyDTO) {
        familyDTO.setId(id);
        return familyService.updateFamily(familyDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFamily(@PathVariable Long id) {
        familyService.deleteFamily(id);
    }

    @PostMapping("/assign-members")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignMembersToFamily(@RequestBody @Valid MemberFamilyAssignmentDTO assignmentDTO) {
        familyService.assignMembersToFamily(assignmentDTO);
    }

    @PostMapping("/assign-members-at-once")
    public ResponseEntity<?> assignMembersToFamilyAtOnce(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody @Valid FamilyMemberAssignmentRequestDTO request) {
        try {
            String token = authHeader.substring(7);
            Long templeId = jwtUtil.extractTempleId(token);
            if (templeId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid token: Temple ID not found");
            }

            FamilyDTO result = familyService.assignMembersToFamilyAtOnce(
                templeId,
                request.getVillageId(),
                request.getFamily(),
                request.getMembers(),
                request.getDanaAssignments() != null ? request.getDanaAssignments() : List.of()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(result);

        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Temple-Village relationship not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Village is not assigned to this temple. Please assign the village first.");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred while processing your request");
        }
    }
}
