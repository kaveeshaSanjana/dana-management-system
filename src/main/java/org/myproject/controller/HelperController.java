package org.myproject.controller;


import lombok.RequiredArgsConstructor;
import org.myproject.dto.HelperDTO;
import org.myproject.service.HelperService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/helper")
@RequiredArgsConstructor
public class HelperController {
    private HelperService helperService;

    @PostMapping()
    public HelperDTO createHelper(HelperDTO helperDTO) {
        return helperService.create(helperDTO);
    }

    @GetMapping("/{id}")
    public HelperDTO getHelperById(@PathVariable Long id) {
        return helperService.findById(id);
    }

    @GetMapping
    public List<HelperDTO> getAllHelpers() {
        return helperService.findAll();
    }

    @GetMapping("/temple/{id}")
    public List<HelperDTO> getAllHelpersByTemple(@PathVariable Long id) {
        return helperService.findAllByTemple(id);
    }

    @PutMapping("/{id}")
    public HelperDTO updateHelper(@PathVariable Long id, @RequestBody HelperDTO helperDTO) {
        return helperService.update(id, helperDTO);
    }
}
