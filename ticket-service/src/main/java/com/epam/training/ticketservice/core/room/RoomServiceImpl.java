package com.epam.training.ticketservice.core.room;

import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.presistence.entity.Room;
import com.epam.training.ticketservice.core.room.presistence.repository.RoomRepository;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public void addRoom(RoomDto roomDto) {
        Room room = new Room(roomDto.getName(), roomDto.getNumberOfSeatRows(), roomDto.getNumberOfSeatColumns());
        roomRepository.save(room);
    }

    @Override
    public void updateRoom(RoomDto roomDto) {
        Optional<Room> room = roomRepository.findByName(roomDto.getName());
        room.get().setNumberOfSeatRows(roomDto.getNumberOfSeatRows());
        room.get().setNumberOfSeatColumns(roomDto.getNumberOfSeatColumns());
        roomRepository.save(room.get());
    }

    @Override
    public void removeRoom(String name) {
        Optional<Room> room = roomRepository.findByName(name);
        roomRepository.delete(room.get());

    }

    @Override
    public List<RoomDto> getRoomList() {
        return roomRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private RoomDto convertEntityToDto(Room room) {
        return new RoomDto(room.getName(), room.getNumberOfSeatRows(), room.getNumberOfSeatColumns());
    }
}
