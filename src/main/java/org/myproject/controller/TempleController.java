package org.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.dto.TempleDTO;
import org.myproject.enums.District;
import org.myproject.enums.Province;
import org.myproject.enums.Town;
import org.myproject.service.TempleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temple")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TempleController {

    private final TempleService templeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TempleDTO> getAllTemples() {
        return templeService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TempleDTO getTempleById(@PathVariable Long id) {
        return templeService.findById(id);
    }

    @GetMapping("/village/{villageId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TempleDTO> getTemplesByVillage(@PathVariable Long villageId) {
        return templeService.findByVillageId(villageId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TempleDTO createTemple(@Valid @RequestBody TempleDTO templeDTO) {
        return templeService.create(templeDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TempleDTO updateTemple(@PathVariable Long id, @Valid @RequestBody TempleDTO templeDTO) {
        return templeService.update(id, templeDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTemple(@PathVariable Long id) {
        templeService.delete(id);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<TempleDTO> getFilteredTemples(
            @RequestParam(required = false) Province province,
            @RequestParam(required = false) District district,
            @RequestParam(required = false) Town town) {
        return templeService.findByFilters(province, district, town);
    }
}
