package com.example.bots_backend.controllers;

import com.example.bots_backend.dto.request.BoatDto;
import com.example.bots_backend.dto.results.AddBoatStatus;
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

@RestController
@RequiredArgsConstructor
@Log4j2
public class BoatController {

    private static final String NEW_BOAT_URL = "/newBoat";
    public static final String BOATS_URL = "/boats";

    private final BoatService boatService;

    @PostMapping(value = NEW_BOAT_URL, produces = "application/json")
    public AddBoatStatus createBot(@Valid @RequestBody BoatDto boatDto) {
        AddBoatStatus res = new AddBoatStatus(boatService.createBoat(boatDto));
        return res;
    }

    @GetMapping(value = BOATS_URL, produces = "application/json")
    public List<BoatResult> getAllBot() {
        return boatService.getAllBoat();
    }
}
