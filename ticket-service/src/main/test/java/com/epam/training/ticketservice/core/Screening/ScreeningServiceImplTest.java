package com.epam.training.ticketservice.core.Screening;

import com.epam.training.ticketservice.core.movies.presistence.repository.MovieRepository;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.ScreeningServiceImpl;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.screening.presistence.entity.Screening;
import com.epam.training.ticketservice.core.screening.presistence.repository.ScreeningRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScreeningServiceImplTest {
    private static final Screening ENTITY = new Screening("Harry potter", "Pedersoli", new Date());
    private static final ScreeningDto DTO = new ScreeningDto("Harry potter", "Pedersoli", new Date());
    private final ScreeningRepository screeningRepository = mock(ScreeningRepository.class);
    private final MovieRepository movieRepository = mock(MovieRepository.class);
    private final ScreeningService underTest = new ScreeningServiceImpl(screeningRepository, movieRepository);

    @Test
    void testGetScreeningListShouldReturnAStaticList(){
        // Given
        when(screeningRepository.findAll()).thenReturn(List.of(ENTITY));
        List<ScreeningDto> expected = List.of(DTO);

        // Mockito.when
        List<ScreeningDto> actual = underTest.getScreeningList();

        // Then
        assertEquals(expected, actual);
        Mockito.verify(screeningRepository).findAll();
    }
}
