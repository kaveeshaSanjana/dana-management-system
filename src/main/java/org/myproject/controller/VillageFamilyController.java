package org.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.dto.VillageFamilyDTO;
import org.myproject.service.VillageFamilyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/village-family")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VillageFamilyController {

    private final VillageFamilyService villageFamilyService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VillageFamilyDTO> getAllVillageFamilies() {
        return villageFamilyService.findAll();
    }

    @GetMapping("/{villageId}/{familyId}")
    @ResponseStatus(HttpStatus.OK)
    public VillageFamilyDTO getVillageFamily(
            @PathVariable Long villageId,
            @PathVariable Long familyId) {
        return villageFamilyService.findById(villageId, familyId);
    }

    @GetMapping("/village/{villageId}")
    @ResponseStatus(HttpStatus.OK)
    public List<VillageFamilyDTO> getVillageFamiliesByVillage(@PathVariable Long villageId) {
        return villageFamilyService.findByVillageId(villageId);
    }

    @GetMapping("/family/{familyId}")
    @ResponseStatus(HttpStatus.OK)
    public List<VillageFamilyDTO> getVillageFamiliesByFamily(@PathVariable Long familyId) {
        return villageFamilyService.findByFamilyId(familyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VillageFamilyDTO createVillageFamily(@Valid @RequestBody VillageFamilyDTO villageFamilyDTO) {
        return villageFamilyService.create(villageFamilyDTO);
    }

    @PutMapping("/{villageId}/{familyId}")
    @ResponseStatus(HttpStatus.OK)
    public VillageFamilyDTO updateVillageFamily(
            @PathVariable Long villageId,
            @PathVariable Long familyId,
            @Valid @RequestBody VillageFamilyDTO villageFamilyDTO) {
        return villageFamilyService.update(villageId, familyId, villageFamilyDTO);
    }

    @DeleteMapping("/{villageId}/{familyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVillageFamily(
            @PathVariable Long villageId,
            @PathVariable Long familyId) {
        villageFamilyService.delete(villageId, familyId);
    }
}
