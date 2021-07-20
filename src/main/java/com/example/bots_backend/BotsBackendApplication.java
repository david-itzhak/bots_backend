package com.example.bots_backend;

import com.example.bots_backend.dto.request.BoatDto;
import com.example.bots_backend.services.BoatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class BotsBackendApplication {

    public static final String INITIAL_LIST_OF_BOATS_JSON = "src/main/resources/initial-list-of-boats.json";

    @Autowired
    public BoatService boatService;

    public static void main(String[] args) {
        SpringApplication.run(BotsBackendApplication.class, args);
    }

    @PostConstruct
    private void fillDb(){

        String file = INITIAL_LIST_OF_BOATS_JSON;
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            BoatDto[] array = mapper.readValue(json, BoatDto[].class);
            for(BoatDto boat : array){
                boatService.createBoat(boat);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
