package org.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.dto.HelperDTO;
import org.myproject.service.HelperService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/helper")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HelperController {

    private final HelperService helperService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HelperDTO createHelper(@Valid @RequestBody HelperDTO helperDTO) {
        return helperService.create(helperDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HelperDTO getHelperById(@PathVariable Long id) {
        return helperService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HelperDTO> getAllHelpers() {
        return helperService.findAll();
    }

    @GetMapping("/temple/{templeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<HelperDTO> getHelpersByTemple(@PathVariable Long templeId) {
        return helperService.findAllByTemple(templeId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HelperDTO updateHelper(@PathVariable Long id, @Valid @RequestBody HelperDTO helperDTO) {
        return helperService.update(id, helperDTO);
    }
}
