package org.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.dto.VillageDTO;
import org.myproject.service.VillageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/village")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VillageController {

    private final VillageService villageService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VillageDTO> getAllVillages() {
        return villageService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VillageDTO getVillageById(@PathVariable Long id) {
        return villageService.findById(id);
    }

    @GetMapping("/temple/{templeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<VillageDTO> getVillagesByTemple(@PathVariable Long templeId) {
        return villageService.findByTempleId(templeId);
    }

    @GetMapping("/family/{familyId}")
    @ResponseStatus(HttpStatus.OK)
    public List<VillageDTO> getVillagesByFamily(@PathVariable Long familyId) {
        return villageService.findByFamilyId(familyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VillageDTO createVillage(@Valid @RequestBody VillageDTO villageDTO) {
        return villageService.create(villageDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VillageDTO updateVillage(@PathVariable Long id, @Valid @RequestBody VillageDTO villageDTO) {
        return villageService.update(id, villageDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVillage(@PathVariable Long id) {
        villageService.delete(id);
    }
}
