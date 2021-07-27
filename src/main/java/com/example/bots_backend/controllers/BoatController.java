package com.example.bots_backend.controllers;

import com.example.bots_backend.dto.request.BoatDto;
import com.example.bots_backend.dto.results.BoatResult;
import com.example.bots_backend.services.BoatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Dmitry Itskov
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@Log4j2
public class BoatController {

    public static final String BOATS_URL = "/boats";

    private final BoatService boatService;

    @PostMapping(value = BOATS_URL, produces = "application/json")
    public BoatResult createBot(@Valid @RequestBody BoatDto boatDto) {
        return boatService.createBoat(boatDto);
    }

    @GetMapping(value = BOATS_URL, produces = "application/json")
    public List<BoatResult> getAllBot() {
        return boatService.getAllBoat();
    }

}

