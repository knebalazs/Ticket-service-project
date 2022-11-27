package com.epam.training.ticketservice.core.room.model;

import lombok.Value;

@Value
public class RoomDto {
    String name;
    Integer numberOfSeatRows;
    Integer numberOfSeatColumns;
}
