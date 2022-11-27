package com.epam.training.ticketservice.core.movies.model;

import lombok.Value;

@Value
public class MovieDto {
    String title;
    String genre;
    Integer lengthInMinutes;
}
