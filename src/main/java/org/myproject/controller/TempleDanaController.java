package org.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.dto.TempleDanaDTO;
import org.myproject.service.TempleDanaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temple-dana")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TempleDanaController {

    private final TempleDanaService templeDanaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TempleDanaDTO> getAllTempleDanas() {
        return templeDanaService.findAll();
    }

    @GetMapping("/{templeId}/{danaId}")
    @ResponseStatus(HttpStatus.OK)
    public TempleDanaDTO getTempleDana(
            @PathVariable Long templeId,
            @PathVariable Long danaId) {
        return templeDanaService.findById(templeId, danaId);
    }

    @GetMapping("/temple/{templeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TempleDanaDTO> getTempleDanasByTemple(@PathVariable Long templeId) {
        return templeDanaService.findByTempleId(templeId);
    }

    @GetMapping("/dana/{danaId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TempleDanaDTO> getTempleDanasByDana(@PathVariable Long danaId) {
        return templeDanaService.findByDanaId(danaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TempleDanaDTO createTempleDana(@Valid @RequestBody TempleDanaDTO templeDanaDTO) {
        return templeDanaService.create(templeDanaDTO);
    }

    @PutMapping("/{templeId}/{danaId}")
    @ResponseStatus(HttpStatus.OK)
    public TempleDanaDTO updateTempleDana(
            @PathVariable Long templeId,
            @PathVariable Long danaId,
            @Valid @RequestBody TempleDanaDTO templeDanaDTO) {
        return templeDanaService.update(templeId, danaId, templeDanaDTO);
    }

    @DeleteMapping("/{templeId}/{danaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTempleDana(
            @PathVariable Long templeId,
            @PathVariable Long danaId) {
        templeDanaService.delete(templeId, danaId);
    }
}
