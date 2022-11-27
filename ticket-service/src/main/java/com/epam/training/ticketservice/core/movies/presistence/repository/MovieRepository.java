package com.epam.training.ticketservice.core.movies.presistence.repository;

import com.epam.training.ticketservice.core.movies.presistence.entity.Movie;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findByTitle(String title);
}
