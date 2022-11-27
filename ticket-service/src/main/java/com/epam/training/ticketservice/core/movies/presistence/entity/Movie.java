package com.epam.training.ticketservice.core.movies.presistence.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Movies")
@Data
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String genre;
    private Integer lengthInMinutes;

    public Movie(String title, String genre, Integer lengthInMinutes) {
        this.title = title;
        this.genre = genre;
        this.lengthInMinutes = lengthInMinutes;
    }
}
