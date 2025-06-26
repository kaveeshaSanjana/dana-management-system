package org.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.myproject.config.JwtUtil;
import org.myproject.dto.TempleVillageDTO;
import org.myproject.dto.TempleVillageCreateRequestDTO;
import org.myproject.service.TempleVillageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/temple-village")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TempleVillageController {

    private final TempleVillageService templeVillageService;
    private final JwtUtil jwtUtil;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TempleVillageDTO> getAllTempleVillages() {
        return templeVillageService.findAll();
    }

    @GetMapping("/{templeId}/{villageId}")
    @ResponseStatus(HttpStatus.OK)
    public TempleVillageDTO getTempleVillage(
            @PathVariable Long templeId,
            @PathVariable Long villageId) {
        return templeVillageService.findById(templeId, villageId);
    }

    @GetMapping("/temple/{templeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TempleVillageDTO> getTempleVillagesByTemple(@PathVariable Long templeId) {
        return templeVillageService.findByTempleId(templeId);
    }

    @GetMapping("/village/{villageId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TempleVillageDTO> getTempleVillagesByVillage(@PathVariable Long villageId) {
        return templeVillageService.findByVillageId(villageId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TempleVillageDTO createTempleVillage(@Valid @RequestBody TempleVillageDTO templeVillageDTO) {
        return templeVillageService.create(templeVillageDTO);
    }

    @PutMapping("/{templeId}/{villageId}")
    @ResponseStatus(HttpStatus.OK)
    public TempleVillageDTO updateTempleVillage(
            @PathVariable Long templeId,
            @PathVariable Long villageId,
            @Valid @RequestBody TempleVillageDTO templeVillageDTO) {
        return templeVillageService.update(templeId, villageId, templeVillageDTO);
    }

    @DeleteMapping("/{templeId}/{villageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTempleVillage(
            @PathVariable Long templeId,
            @PathVariable Long villageId) {
        templeVillageService.delete(templeId, villageId);
    }

    @GetMapping("/by-temple/{templeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TempleVillageDTO> getVillagesForTemple(@PathVariable Long templeId) {
        return templeVillageService.findByTempleId(templeId);
    }

    @PostMapping("/assign-village/{villageId}")
    public ResponseEntity<?> assignVillageToTemple(
            @RequestHeader("Authorization") String authHeader,
            @Valid @PathVariable Long villageId) {
        System.err.println("Assigning village with ID: " + villageId + " to temple");
        try {
            String token = authHeader.substring(7); // Remove "Bearer " prefix
            Long templeId = jwtUtil.extractTempleId(token);

            if (templeId == null) {
                return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid token: Temple ID not found");
            }

            // Check if already assigned
            if (templeVillageService.isVillageAssignedToTemple(templeId, villageId)) {
                return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Village is already assigned to this temple");
            }

            TempleVillageDTO result = templeVillageService.assignVillageToTemple(templeId, villageId);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);

        } catch (IllegalStateException e) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
        } catch (Exception e) {
            log.error("Error assigning village to temple: {}", e.getMessage());
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error assigning village to temple: " + e.getMessage());
        }
    }
}
