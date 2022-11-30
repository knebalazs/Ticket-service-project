package com.epam.training.ticketservice.core.screening.model;

import lombok.Value;

import java.util.Date;

@Value
public class ScreeningDto {
    String movieTitle;
    String roomName;
    Date startingTime;
}
