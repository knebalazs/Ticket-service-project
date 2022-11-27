package com.epam.training.ticketservice.core.room.presistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Rooms")
@Data
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer numberOfSeatRows;
    private Integer numberOfSeatColumns;

    public Room(String name, Integer numberOfSeatRows, Integer numberOfSeatColumns) {
        this.name = name;
        this.numberOfSeatRows = numberOfSeatRows;
        this.numberOfSeatColumns = numberOfSeatColumns;
    }
}
