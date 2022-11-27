package com.epam.training.ticketservice.core.movies;

import com.epam.training.ticketservice.core.movies.model.MovieDto;
import com.epam.training.ticketservice.core.movies.presistence.entity.Movie;
import com.epam.training.ticketservice.core.movies.presistence.repository.MovieRepository;

import java.util.List;

public interface MovieService {

    void addMovie(MovieDto movieDto);

    void updateMovie(MovieDto movieDto);

    void removeMovie(String title);

    List<MovieDto> getMovieList();

}
