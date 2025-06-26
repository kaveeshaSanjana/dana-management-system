package org.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.config.JwtUtil;
import org.myproject.dto.TempleDanaAssignmentDTO;
import org.myproject.exception.ResourceNotFoundException;
import org.myproject.service.TempleDanaAssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/temple-dana-assignment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TempleDanaAssignmentController {

    private final TempleDanaAssignmentService assignmentService;
    private final JwtUtil jwtUtil;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TempleDanaAssignmentDTO> getAllAssignments() {
        return assignmentService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TempleDanaAssignmentDTO getAssignmentById(@PathVariable Long id) {
        return assignmentService.findById(id);
    }

    @GetMapping("/temple-dana/{templeId}/{danaId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TempleDanaAssignmentDTO> getAssignmentsByTempleDana(
            @PathVariable Long templeId,
            @PathVariable Long danaId) {
        return assignmentService.findByTempleDanaId(templeId, danaId);
    }

    @GetMapping("/family/{familyId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TempleDanaAssignmentDTO> getAssignmentsByFamily(@PathVariable Long familyId) {
        return assignmentService.findByFamilyId(familyId);
    }

    @GetMapping("/by-family-ids")
    @ResponseStatus(HttpStatus.OK)
    public List<TempleDanaAssignmentDTO> getAssignmentsByFamilyIds(@RequestHeader("Authorization") String authHeader) {
        List<Long> longs = jwtUtil.extractFamilyIds(authHeader.substring(7));
        return assignmentService.findByFamilyIds(longs);
    }

    @GetMapping("/date/{date}")
    @ResponseStatus(HttpStatus.OK)
    public List<TempleDanaAssignmentDTO> getAssignmentsByDate(@RequestHeader("Authorization") String authHeader
                                                            , @PathVariable("date")LocalDate date) {
        System.out.println(date);
        return assignmentService.findByDateAndTemple(jwtUtil.extractTempleId(authHeader.substring(7))
                                                    ,date);
    }

    @PostMapping
    public TempleDanaAssignmentDTO createAssignment(
            @Valid @RequestBody TempleDanaAssignmentDTO assignmentDTO,
            @RequestHeader("Authorization") String token) {
        return assignmentService.create(assignmentDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TempleDanaAssignmentDTO updateAssignment(
            @PathVariable Long id,
            @Valid @RequestBody TempleDanaAssignmentDTO assignmentDTO) {
        return assignmentService.update(id, assignmentDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAssignment(@PathVariable Long id) {
        assignmentService.delete(id);
    }


    @PostMapping("/conform/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void conformDana(@RequestHeader("Authorization") String authHeader,
                            @PathVariable Long id) {
        List<Long> familyIds = jwtUtil.extractFamilyIds(authHeader.substring(7));
        TempleDanaAssignmentDTO assignment = assignmentService.findById(id);

        if (assignment == null) {
            throw new ResourceNotFoundException("Dana assignment not found with id: " + id);
        }

        if (!familyIds.contains(assignment.getFamily().getId())) {
            throw new AccessDeniedException("You don't have permission to confirm this dana");
        }

        if (assignment.getIsConfirmed()) {
            throw new IllegalStateException("Dana is already confirmed");
        }

        assignment.setIsConfirmed(true);
        assignmentService.update(id, assignment);
    }

    @GetMapping("/by-member/{phoneNumber}")
    @ResponseStatus(HttpStatus.OK)  // Changed from NO_CONTENT since we're returning data
    public List<TempleDanaAssignmentDTO> findByPhoneNumber(@RequestHeader("Authorization") String authHeader,
                                                           @PathVariable String phoneNumber) {
        Long templeId = jwtUtil.extractTempleId(authHeader.substring(7));
        List<TempleDanaAssignmentDTO> assignments = assignmentService.findByPhoneNumber(templeId, phoneNumber);
        return assignments;
    }


}
