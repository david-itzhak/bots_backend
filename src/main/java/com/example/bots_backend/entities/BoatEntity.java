package com.example.bots_backend.entities;

import lombok.*;

import javax.persistence.*;

/**
 * @author Dmitry Itskov
 */
@Entity
@Table(name = "bots")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class BoatEntity {

    @Id
    String id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "shipyardName", nullable = false)
    public String shipyardName;

    @Column(name = "shipyardCountry", nullable = false)
    public String shipyardCountry;

    @Column(name = "model", nullable = false)
    public String model;

    @Column(name = "year", nullable = false)
    public Integer year;

    @Column(name = "size", nullable = false)
    public Integer size;

    @Column(name = "type", nullable = false)
    public Integer type;

}
