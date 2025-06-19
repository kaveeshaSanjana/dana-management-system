package org.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.dto.HeadMonkDTO;
import org.myproject.service.HeadMonkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/head-monk")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HeadMonkController {

    private final HeadMonkService headMonkService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HeadMonkDTO> getAllHeadMonks() {
        return headMonkService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HeadMonkDTO getHeadMonkById(@PathVariable Long id) {
        return headMonkService.findById(id);
    }

    @GetMapping("/temple/{templeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<HeadMonkDTO> getHeadMonksByTempleId(@PathVariable Long templeId) {
        return headMonkService.findByTempleId(templeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HeadMonkDTO createHeadMonk(@Valid @RequestBody HeadMonkDTO headMonkDTO) {
        return headMonkService.create(headMonkDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HeadMonkDTO updateHeadMonk(@PathVariable Long id, @Valid @RequestBody HeadMonkDTO headMonkDTO) {
        headMonkDTO.setId(id);
        return headMonkService.update(headMonkDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHeadMonk(@PathVariable Long id) {
        headMonkService.delete(id);
    }
}
