package org.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.config.JwtUtil;
import org.myproject.dto.TempleDanaDTO;
import org.myproject.dto.TempleDanaDTOForTDC;
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
    private final JwtUtil jwtUtil;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TempleDanaDTOForTDC> getAllTempleDanas() {
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
    public List<TempleDanaDTOForTDC> getTempleDanasByTemple(@PathVariable Long templeId) {
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

    @PostMapping("/assign/{danaId}/{minCount}")
    @ResponseStatus(HttpStatus.CREATED)
    public TempleDanaDTO assignDanaToTemple(@Valid @PathVariable("danaId") Long danaId,
                                            @Valid @PathVariable("minCount") Integer minCount,
                                            @RequestHeader("Authorization") String authHeader) {
        Long templeId = jwtUtil.extractTempleId(authHeader.substring(7));
        System.err.println(templeId+" "+danaId+" "+ minCount+" ");
        return templeDanaService.assignDanaToTemple(templeId,danaId,minCount );
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
