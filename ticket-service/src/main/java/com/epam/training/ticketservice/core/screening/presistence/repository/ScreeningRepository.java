package com.epam.training.ticketservice.core.screening.presistence.repository;

import com.epam.training.ticketservice.core.screening.presistence.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
    Optional<Screening> findByMovieTitleAndRoomNameAndStartingTime(String movieTitle, String roomName, Date startingTime);
}
