package com.epam.training.ticketservice.core.movies;

import com.epam.training.ticketservice.core.movies.model.MovieDto;
import com.epam.training.ticketservice.core.movies.presistence.entity.Movie;
import com.epam.training.ticketservice.core.movies.presistence.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public void addMovie(MovieDto movieDto) {
        Movie movie = new Movie(movieDto.getTitle(), movieDto.getGenre(), movieDto.getLengthInMinutes());
        movieRepository.save(movie);
    }

    @Override
    public void updateMovie(MovieDto movieDto) {
        Optional<Movie> movie = movieRepository.findByTitle(movieDto.getTitle());
        movie.get().setGenre(movieDto.getGenre());
        movie.get().setLengthInMinutes(movieDto.getLengthInMinutes());
        movieRepository.save(movie.get());
    }

    @Override
    public void removeMovie(String title) {
        Optional<Movie> movie = movieRepository.findByTitle(title);
        movieRepository.delete(movie.get());
    }

    @Override
    public List<MovieDto> getMovieList() {
        return movieRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private MovieDto convertEntityToDto(Movie movie) {
        return new MovieDto(movie.getTitle(), movie.getGenre(), movie.getLengthInMinutes());
    }

}
