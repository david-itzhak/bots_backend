package com.example.bots_backend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * @author Dmitry Itskov
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoatDto {

    @Size(min = 3, max = 200)
    @NotEmpty
    @NotNull
    @JsonProperty(value = "id", required = true)
    public String id;

    @Size(min = 3, max = 200)
    @NotEmpty
    @NotNull
    @JsonProperty(value = "name", required = true)
    public String name;

    @NotNull
    @JsonProperty(value = "shipyard", required = true)
    public Shipyard shipyard;

    @NotNull
    @JsonProperty(value = "model", required = true)
    public Model model;

    @Min(1)
    @JsonProperty(value = "size", required = true)
    @NotNull
    public Integer size;

    @Min(1)
    @JsonProperty(value = "type", required = true)
    @NotNull
    public Integer type;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Shipyard {

        @Size(min = 3, max = 200)
        @JsonProperty(value = "name", required = true)
        @NotEmpty
        @NotNull
        String name;

        @Size(min = 3, max = 200)
        @JsonProperty(value = "country", required = true)
        @NotEmpty
        @NotNull
        String country;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Model{

        @Size(min = 3, max = 200)
        @JsonProperty(value = "model", required = true)
        @NotEmpty
        @NotNull
        String model;

        @Min(1800)
        @JsonProperty(value = "year", required = true)
        @NotNull
        Integer year;
    }

}
