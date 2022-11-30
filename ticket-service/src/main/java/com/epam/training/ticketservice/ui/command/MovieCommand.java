package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movies.MovieService;
import com.epam.training.ticketservice.core.movies.model.MovieDto;
import com.epam.training.ticketservice.core.movies.presistence.entity.Movie;
import com.epam.training.ticketservice.core.user.User;
import com.epam.training.ticketservice.core.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
@AllArgsConstructor
public class MovieCommand {

    private final MovieService movieService;
    private final UserService userService;

    @ShellMethod(key = "create movie", value = "Creates a movie")
    public String createMovie(String title, String genre, Integer lengthInMinutes) throws Exception {
        if (userService.describeUser().getRole().equals(User.Role.USER)) {
            throw new Exception("only admin user can create movies");
        }
        MovieDto movieDto = new MovieDto(title, genre, lengthInMinutes);
        movieService.addMovie(movieDto);
        return "movie created";
    }

    @ShellMethod(key = "update movie", value = "Updates a movie")
    public String updateMovie(String title, String genre, Integer lengthInMinutes) throws Exception {
        MovieDto movieDto = new MovieDto(title, genre, lengthInMinutes);
        if (userService.describeUser().getRole().equals(User.Role.USER)) {
            throw new Exception("only admin user can update movies");
        }
        try {
            movieService.updateMovie(movieDto);
            return "movie updated";
        } catch (Exception e) {
            return "invalid movie";
        }
    }

    @ShellMethod(key = "delete movie", value = "Deletes a movie")
    public String deleteMovie(String title) throws Exception {
        if (userService.describeUser().getRole().equals(User.Role.USER)) {
            throw new Exception("only admin user can delete movies");
        }
        try {
            movieService.removeMovie(title);
            return "movie deleted";
        } catch (Exception e) {
            return "invalid movie";
        }
    }

    @ShellMethod(key = "list movies", value = "Lists movies")
    public String listMovies() {
        List<MovieDto> movieList = movieService.getMovieList();
        StringBuilder mlist = new StringBuilder();
        if (movieService.getMovieList().isEmpty()) {
            return "There are no movies at the moment";
        }
        for (MovieDto m : movieList) {
            mlist.append(m.getTitle()).append(" (").append(m.getGenre()).append(", ").append(m.getLengthInMinutes()).append(" minutes)\n");
        }
        return mlist.toString();
    }
}
