package com.example.bots_backend.services;

import com.example.bots_backend.dto.request.BoatDto;
import com.example.bots_backend.dto.results.BoatResult;
import com.example.bots_backend.entities.BoatEntity;
import com.example.bots_backend.repositories.BoatRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Dmitry Itskov
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class BoatService {

    private final BoatRepo boatRepo;

    public BoatResult createBoat(BoatDto boatDto) {
        BoatEntity campaign;
        try {
            campaign = boatRepo.save(new BoatEntity(
                    boatDto.getId() == null? "boat-" + generatingRandomString() : boatDto.getId(),
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
            throw new InternalError(e.getMessage());
        }
        return convertBoatEntityToBoatResult(campaign);
    }

    public List<BoatResult> getAllBoat() {
        Optional<List<BoatEntity>> boatsOptional = Optional.ofNullable(boatRepo.findAll());
        return boatsOptional.isPresent() ? converBoatEntityListToBoatResultList(boatsOptional.get()) : Collections.emptyList();
    }

    private List<BoatResult> converBoatEntityListToBoatResultList(List<BoatEntity> listBoatEntity){
        return listBoatEntity.stream().map(this::convertBoatEntityToBoatResult).collect(Collectors.toList());
    }

    private BoatResult convertBoatEntityToBoatResult(BoatEntity boatEntity){
        return new BoatResult(
                boatEntity.getId(),
                boatEntity.getName(),
                new BoatResult.Shipyard(boatEntity.shipyardName, boatEntity.getShipyardCountry()),
                new BoatResult.Model(boatEntity.getModel(), boatEntity.getYear()),
                boatEntity.getSize(),
                boatEntity.getType());
    }

    private String generatingRandomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}
