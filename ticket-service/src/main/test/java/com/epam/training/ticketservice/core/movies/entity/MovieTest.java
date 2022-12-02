package com.epam.training.ticketservice.core.movies.entity;

import com.epam.training.ticketservice.core.movies.presistence.entity.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {
    private Movie underTest = new Movie("title","genre",60);

    @Test
    void testNotEqualsWithEmpty(){
        Movie empty = new Movie();

        assertNotEquals(empty,underTest);
        assertNotEquals(empty.hashCode(), underTest.hashCode());
    }

    @Test
    void testEqualsWithSame(){
        Movie same = new Movie("title","genre",60);

        assertEquals(same,underTest);
        assertEquals(same.hashCode(), underTest.hashCode());
    }
    @Test
    void testEqualsWithNotSame(){
        Movie notSame = new Movie("title2","genre",30);

        assertNotEquals(notSame,underTest);
        assertNotEquals(notSame.hashCode(), underTest.hashCode());
    }

}
