package com.epam.training.ticketservice.core.Screening;

import com.epam.training.ticketservice.core.movies.presistence.entity.Movie;
import com.epam.training.ticketservice.core.movies.presistence.repository.MovieRepository;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.ScreeningServiceImpl;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.screening.presistence.entity.Screening;
import com.epam.training.ticketservice.core.screening.presistence.repository.ScreeningRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScreeningServiceImplTest {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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

    @Test
    void testCreateScreeningShouldStoreTheGivenScreeningWhenTheInputScreeningIsValid() {
        // Given
        Mockito.when(screeningRepository.save(ENTITY)).thenReturn(ENTITY);

        // Mockito.when
        underTest.addScreening(DTO);

        // Then
        Mockito.verify(screeningRepository).save(ENTITY);
    }
    @Test
    void testIsThereAnOverlapShouldBeTrueWhenThereIsAnOverlapAndNoBreak() throws ParseException {
        // Given
        ENTITY.setStartingTime(dateFormat.parse("2021-03-14 16:00"));
        Mockito.when(screeningRepository.findAll()).thenReturn(List.of(ENTITY));
        Mockito.when(movieRepository.findByTitle(ENTITY.getMovieTitle())).thenReturn(Optional.of(new Movie(ENTITY.getMovieTitle(), "fantasy", 120)));
        ScreeningDto screeningDto2 = new ScreeningDto("title2", "Pedersoli", dateFormat.parse("2021-03-14 17:00"));
        boolean expected = true;
        // Mockito.when
        boolean actual = underTest.isThereAnOverlap(screeningDto2,false);

        // Then
        assertEquals(expected,actual);
    }
    @Test
    void testIsThereAnOverlapShouldBeFalseWhenThereIsNotAnOverlapAndNoBreak() throws ParseException {
        // Given
        ENTITY.setStartingTime(dateFormat.parse("2021-03-14 16:00"));
        Mockito.when(screeningRepository.findAll()).thenReturn(List.of(ENTITY));
        Mockito.when(movieRepository.findByTitle(ENTITY.getMovieTitle())).thenReturn(Optional.of(new Movie(ENTITY.getMovieTitle(), "fantasy", 120)));
        ScreeningDto screeningDto2 = new ScreeningDto("title2", "Pedersoli", dateFormat.parse("2021-03-14 18:30"));
        boolean expected = false;
        // Mockito.when
        boolean actual = underTest.isThereAnOverlap(screeningDto2,false);

        // Then
        assertEquals(expected,actual);
    }

    @Test
    void testIsThereAnOverlapShouldBeTrueWhenThereIsAnOverlapAndBreak() throws ParseException {
        // Given
        ENTITY.setStartingTime(dateFormat.parse("2021-03-14 16:00"));
        Mockito.when(screeningRepository.findAll()).thenReturn(List.of(ENTITY));
        Mockito.when(movieRepository.findByTitle(ENTITY.getMovieTitle())).thenReturn(Optional.of(new Movie(ENTITY.getMovieTitle(), "fantasy", 120)));
        ScreeningDto screeningDto2 = new ScreeningDto("title2", "Pedersoli", dateFormat.parse("2021-03-14 18:10"));
        boolean expected = true;
        // Mockito.when
        boolean actual = underTest.isThereAnOverlap(screeningDto2,true);

        // Then
        assertEquals(expected,actual);
    }

}
