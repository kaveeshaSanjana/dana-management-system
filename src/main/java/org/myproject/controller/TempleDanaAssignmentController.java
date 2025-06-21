package org.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.config.JwtUtil;
import org.myproject.dto.TempleDanaAssignmentDTO;
import org.myproject.service.TempleDanaAssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}


