package com.epam.training.ticketservice.core.room.presistence.repository;

import com.epam.training.ticketservice.core.room.presistence.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Optional<Room> findByName(String name);
}
