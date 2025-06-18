package org.myproject.controller;

import lombok.RequiredArgsConstructor;
import org.myproject.dto.HeadMonkDTO;
import org.myproject.service.HeadMonkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/head-monk")
@RequiredArgsConstructor
public class HeadMonkController {

    private HeadMonkService headMonkService;

    @GetMapping
    public List<HeadMonkDTO> getAllHeadMonks() {
        return headMonkService.findAll();
    }

    @GetMapping("/{id}")
    public HeadMonkDTO getHeadMonkById(@PathVariable Long id) {
        return headMonkService.findById(id);
    }

    @GetMapping("/temple/{templeId}")
    public List<HeadMonkDTO> getHeadMonksByTempleId(@PathVariable Long templeId) {
        return headMonkService.findByTempleId(templeId);
    }

}
