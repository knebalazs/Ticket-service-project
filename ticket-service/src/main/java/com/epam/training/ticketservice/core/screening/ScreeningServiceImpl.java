package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.movies.presistence.repository.MovieRepository;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.presistence.entity.Room;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.screening.presistence.entity.Screening;
import com.epam.training.ticketservice.core.screening.presistence.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;

    @Override
    public void addScreening(ScreeningDto screeningDto) {
        Screening screening = new Screening(screeningDto.getMovieTitle(),
                screeningDto.getRoomName(), screeningDto.getStartingTime());
        screeningRepository.save(screening);

    }

    @Override
    public void removeScreening(ScreeningDto screeningDto) {
        Optional<Screening> screening = screeningRepository
                .findByMovieTitleAndRoomNameAndStartingTime(screeningDto.getMovieTitle(),
                        screeningDto.getRoomName(), screeningDto.getStartingTime());
        screeningRepository.delete(screening.get());
    }

    @Override
    public Boolean isThereAnOverlap(ScreeningDto screeningDto) {
        for (ScreeningDto s : getScreeningList()) {
            if (s.getRoomName().equals(screeningDto.getRoomName())) {
                Long timeDiff = (s.getStartingTime().getTime() - screeningDto.getStartingTime().getTime()) / 60000;
                System.out.println(timeDiff);
                if (timeDiff >= 0) {
                    return timeDiff < movieRepository.findByTitle(s.getMovieTitle()).get().getLengthInMinutes();
                } else {
                    return timeDiff + movieRepository.findByTitle(screeningDto.getMovieTitle())
                            .get().getLengthInMinutes() > 0;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean isTimeInBrakeTime(ScreeningDto screeningDto) {
        return null;
    }

    @Override
    public List<ScreeningDto> getScreeningList() {
        return screeningRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private ScreeningDto convertEntityToDto(Screening screening) {
        return new ScreeningDto(screening.getMovieTitle(), screening.getRoomName(), screening.getStartingTime());
    }

    private Boolean checkTheIfTimeIsInBreak(Date startingTime, Date endingTime) {
        return true;
    }
}
