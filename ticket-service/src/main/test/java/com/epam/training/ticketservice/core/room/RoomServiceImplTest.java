package com.epam.training.ticketservice.core.room;

import com.epam.training.ticketservice.core.movies.MovieService;
import com.epam.training.ticketservice.core.movies.MovieServiceImpl;
import com.epam.training.ticketservice.core.movies.model.MovieDto;
import com.epam.training.ticketservice.core.movies.presistence.entity.Movie;
import com.epam.training.ticketservice.core.movies.presistence.repository.MovieRepository;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.presistence.entity.Room;
import com.epam.training.ticketservice.core.room.presistence.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoomServiceImplTest {
    private static final Room ENTITY = new Room("Pedersoli", 10, 10);
    private static final RoomDto DTO = new RoomDto("Pedersoli", 10, 10);
    private final RoomRepository roomRepository = mock(RoomRepository.class);
    private final RoomService underTest = new RoomServiceImpl(roomRepository);

    @Test
    void testGetRoomListShouldReturnAStaticList(){
        // Given
        when(roomRepository.findAll()).thenReturn(List.of(ENTITY));
        List<RoomDto> expected = List.of(DTO);

        // Mockito.when
        List<RoomDto> actual = underTest.getRoomList();

        // Then
        assertEquals(expected, actual);
        Mockito.verify(roomRepository).findAll();
    }

    @Test
    void testCreateRoomShouldStoreTheGivenRoomWhenTheInputRoomIsValid() {
        // Given
        Mockito.when(roomRepository.save(ENTITY)).thenReturn(ENTITY);

        // Mockito.when
        underTest.addRoom(DTO);

        // Then
        Mockito.verify(roomRepository).save(ENTITY);
    }
}
