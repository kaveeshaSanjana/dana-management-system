package org.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.dto.TempleVillageDTO;
import org.myproject.service.TempleVillageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temple-village")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TempleVillageController {

    private final TempleVillageService templeVillageService;

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
}
