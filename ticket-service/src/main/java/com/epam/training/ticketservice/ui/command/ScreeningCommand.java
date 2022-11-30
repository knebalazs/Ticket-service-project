package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movies.presistence.entity.Movie;
import com.epam.training.ticketservice.core.movies.presistence.repository.MovieRepository;
import com.epam.training.ticketservice.core.room.presistence.repository.RoomRepository;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.user.User;
import com.epam.training.ticketservice.core.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ShellComponent
@AllArgsConstructor
public class ScreeningCommand {

    private final UserService userService;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final ScreeningService screeningService;


    @ShellMethod(key = "create screening", value = "Creates a screening")
    public String createScreening(String movieTitle, String nameOfRoom, String startingTime) throws Exception {
        if (userService.describeUser().getRole().equals(User.Role.USER)) {
            throw new Exception("only admin user can create screenings");
        }
        if (movieRepository.findByTitle(movieTitle).isEmpty()) {
            throw new Exception("this movie doesn't exist");
        }
        if (roomRepository.findByName(nameOfRoom).isEmpty()) {
            throw new Exception("this room doesn't exist");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ScreeningDto screeningDto = new ScreeningDto(movieTitle, nameOfRoom, dateFormat.parse(startingTime));


        if (screeningService.isThereAnOverlap(screeningDto)) {
            return "There is an overlapping screening";
        }
        if (screeningService.isThereAnOverlap(screeningDto)) {
            return "This would start in the break period after another screening in this room";
        }
        screeningService.addScreening(screeningDto);
        return "screening created";
    }

    @ShellMethod(key = "delete screening", value = "Deletes a screening")
    public String deleteScreening(String movieTitle, String nameOfRoom, Date startingTime) throws Exception {
        if (userService.describeUser().getRole().equals(User.Role.USER)) {
            throw new Exception("only admin user can create screening");
        }
        try {
            ScreeningDto screeningDto = new ScreeningDto(movieTitle, nameOfRoom, startingTime);
            screeningService.removeScreening(screeningDto);
            return "room deleted";
        } catch (Exception e) {
            return "invalid room";
        }
    }

    @ShellMethod(key = "list screenings", value = "Lists screenings")
    public String listScreenings() {
        List<ScreeningDto> screeningDtos = screeningService.getScreeningList();
        StringBuilder screening = new StringBuilder();
        if (screeningDtos.isEmpty()) {
            return "There are no screenings";
        }
        for (ScreeningDto s : screeningDtos) {
            Optional<Movie> currentMovie = movieRepository.findByTitle(s.getMovieTitle());
            screening.append(s.getMovieTitle()).append(" (").append(currentMovie.get().getGenre())
                    .append(", ").append(currentMovie.get().getLengthInMinutes()).append(" minutes), screened in room ")
                    .append(s.getRoomName()).append(", at ").append(s.getStartingTime().toString().substring(0,16))
                    .append("\n");
        }
        return screening.toString();
    }
}
