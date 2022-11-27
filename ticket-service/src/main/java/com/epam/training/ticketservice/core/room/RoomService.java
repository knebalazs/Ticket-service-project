package com.epam.training.ticketservice.core.room;

import com.epam.training.ticketservice.core.room.model.RoomDto;

import java.util.List;

public interface RoomService {

    void addRoom(RoomDto roomDto);

    void updateRoom(RoomDto roomDto);

    void removeRoom(String name);

    List<RoomDto> getRoomList();

}
