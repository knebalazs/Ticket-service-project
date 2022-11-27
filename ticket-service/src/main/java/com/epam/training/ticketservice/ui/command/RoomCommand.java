package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movies.model.MovieDto;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.user.User;
import com.epam.training.ticketservice.core.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
@AllArgsConstructor
public class RoomCommand {

    private final RoomService roomService;
    private final UserService userService;

    @ShellMethod(key = "create room", value = "Creates a room")
    public String createRoom(String name, Integer numberOfSeatRows, Integer numberOfSeatColumns) throws Exception {
        if (userService.describeUser().getRole().equals(User.Role.USER)) {
            throw new Exception("only admin user can create rooms");
        }
        RoomDto roomDto = new RoomDto(name, numberOfSeatRows, numberOfSeatColumns);
        roomService.addRoom(roomDto);
        return "room created";
    }

    @ShellMethod(key = "update room", value = "Updates a room")
    public String updateRoom(String name, Integer numberOfSeatRows, Integer numberOfSeatColumns) throws Exception {
        RoomDto roomDto = new RoomDto(name, numberOfSeatRows, numberOfSeatColumns);
        if (userService.describeUser().getRole().equals(User.Role.USER)) {
            throw new Exception("only admin user can update rooms");
        }
        try {
            roomService.updateRoom(roomDto);
            return "room updated";
        } catch (Exception e) {
            return "invalid room";
        }
    }

    @ShellMethod(key = "delete room", value = "Deletes a room")
    public String deleteRoom(String name) throws Exception {
        if (userService.describeUser().getRole().equals(User.Role.USER)) {
            throw new Exception("only admin user can delete rooms");
        }
        try {
            roomService.removeRoom(name);
            return "room deleted";
        } catch (Exception e) {
            return "invalid room";
        }
    }

    @ShellMethod(key = "list rooms", value = "Lists rooms")
    public String listRooms() {
        List<RoomDto> roomDtos = roomService.getRoomList();
        StringBuilder rooms = new StringBuilder();
        if (roomDtos.isEmpty()) {
            return "There are no rooms at the moment";
        }
        for (RoomDto r : roomDtos) {
            rooms.append("Room ").append(r.getName()).append(" with ")
                    .append(r.getNumberOfSeatRows() * r.getNumberOfSeatColumns())
                    .append(" seats, ").append(r.getNumberOfSeatRows()).append(" rows and ")
                    .append(r.getNumberOfSeatColumns()).append(" columns\n");
        }
        return rooms.toString();
    }
}
