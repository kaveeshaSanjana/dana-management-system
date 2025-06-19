package org.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.dto.FamilyDTO;
import org.myproject.service.FamilyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/family")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FamilyController {

    private final FamilyService familyService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FamilyDTO> getAllFamilies() {
        return familyService.getAllFamilies();
    }

    @GetMapping("/by-user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FamilyDTO> getAllFamiliesByUserId(@PathVariable Long userId) {
        return familyService.getAllFamiliesByUserId(userId);
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
}
