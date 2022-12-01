package com.epam.training.ticketservice.core.movies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.epam.training.ticketservice.core.movies.model.MovieDto;
import com.epam.training.ticketservice.core.movies.presistence.entity.Movie;
import com.epam.training.ticketservice.core.movies.presistence.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class MovieServiceImplTest {

    private static final Movie ENTITY = new Movie("Harry potter", "fantasy", 120);
    private static final MovieDto DTO = new MovieDto("Harry potter", "fantasy", 120);
    private final MovieRepository  movieRepository = mock(MovieRepository.class);
    private final MovieService underTest = new MovieServiceImpl(movieRepository);

    @Test
    void testGetMovieListShouldReturnAStaticList(){
        // Given
        when(movieRepository.findAll()).thenReturn(List.of(ENTITY));
        List<MovieDto> expected = List.of(DTO);

        // Mockito.when
        List<MovieDto> actual = underTest.getMovieList();

        // Then
        assertEquals(expected, actual);
        Mockito.verify(movieRepository).findAll();
    }

    @Test
    void testCreateMovieShouldStoreTheGivenMovieWhenTheInputMovieIsValid() {
        // Given
        Mockito.when(movieRepository.save(ENTITY)).thenReturn(ENTITY);

        // Mockito.when
        underTest.addMovie(DTO);

        // Then
        Mockito.verify(movieRepository).save(ENTITY);
    }

}
