package com.example.bots_backend.dto.results;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Dmitry Itskov
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoatResult {

    public String id;
    public String name;
    public Shipyard shipyard;
    public Model model;
    public Integer size;
    public Integer type;

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Shipyard {
        String name;
        String country;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Model{
        String model;
        Integer year;
    }
}
