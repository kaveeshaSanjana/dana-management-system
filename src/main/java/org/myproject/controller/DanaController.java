package org.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.dto.DanaDTO;
import org.myproject.service.DanaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dana")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DanaController {

    private final DanaService danaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DanaDTO> getAllDanas() {
        return danaService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DanaDTO getDanaById(@PathVariable Long id) {
        return danaService.findById(id);
    }

    @GetMapping("/temple/{templeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DanaDTO> getDanasByTemple(@PathVariable Long templeId) {
        return danaService.findByTempleDanasTempleId(templeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DanaDTO createDana(@Valid @RequestBody DanaDTO danaDTO) {
        return danaService.create(danaDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DanaDTO updateDana(@PathVariable Long id, @Valid @RequestBody DanaDTO danaDTO) {
        return danaService.update(id, danaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDana(@PathVariable Long id) {
        danaService.delete(id);
    }
}
