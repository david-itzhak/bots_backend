package com.example.bots_backend.services;

import com.example.bots_backend.dto.request.BoatDto;
import com.example.bots_backend.dto.results.BoatResult;
import com.example.bots_backend.entities.BoatEntity;
import com.example.bots_backend.repositories.BoatRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Dmitry Itskov
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class BoatService {

    private final BoatRepo boatRepo;

    public Boolean createBoat(BoatDto boatDto) {
        try {
            BoatEntity campaign = boatRepo.save(new BoatEntity(
                    boatDto.getId(),
                    boatDto.getName(),
                    boatDto.shipyard.getName(),
                    boatDto.shipyard.getCountry(),
                    boatDto.model.getModel(),
                    boatDto.model.getYear(),
                    boatDto.getSize(),
                    boatDto.getType()
            ));
        } catch (Exception e){
            log.error(e.getMessage(), e.getCause());
            return false;
        }
        return true;
    }

    public List<BoatResult> getAllBoat() {
        Optional<List<BoatEntity>> boatsOptional = Optional.ofNullable(boatRepo.findAll());
        return boatsOptional.isPresent() ? converBoatEntityListToBoatResultList(boatsOptional.get()) : Collections.emptyList();
    }

    private List<BoatResult> converBoatEntityListToBoatResultList(List<BoatEntity> listBoatEntity){
        return listBoatEntity.stream().map(this::convertBoatEntityToBoatResult).collect(Collectors.toList());
    }

    private BoatResult convertBoatEntityToBoatResult(BoatEntity x){
        return new BoatResult(
                x.getId(),
                x.getName(),
                new BoatResult.Shipyard(x.shipyardName, x.getShipyardCountry()),
                new BoatResult.Model(x.getModel(), x.getYear()),
                x.getSize(),
                x.getType());
    }
}
