package com.epam.training.ticketservice.ui.command;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Date;

@ShellComponent
@AllArgsConstructor
public class ScreeningCommand {

    @ShellMethod(key = "create screening", value = "Creates a screening")
    public String createScreening(String movieTitle, String nameOfRoom, Date startingTime) {
        return "";
    }

    @ShellMethod(key = "delete screening", value = "Deletes a screening")
    public String deleteScreening(String movieTitle, String nameOfRoom, Date startingTime) {
        return "";
    }

    @ShellMethod(key = "list screenings", value = "Lists screenings")
    public String listScreenings() {
        return "";
    }
}
