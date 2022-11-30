package com.epam.training.ticketservice.core.screening.presistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Screenings")
@Data
@NoArgsConstructor
public class Screening {
    @Id
    @GeneratedValue
    private Integer id;
    private String movieTitle;
    private String roomName;
    private Date startingTime;

    public Screening(String movieTitle, String roomName, Date startingTime) {
        this.movieTitle = movieTitle;
        this.roomName = roomName;
        this.startingTime = startingTime;
    }
}
