package com.epam.training.ticketservice.core.movies.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MovieDtoTest {
    private final MovieDto underTest = new MovieDto("title","genre",60);

    @Test
    void testNotEqualsWithEmpty(){
        MovieDto empty = new MovieDto(null,null,null);

        assertNotEquals(empty,underTest);
        assertNotEquals(empty.hashCode(), underTest.hashCode());
    }

    @Test
    void testEqualsWithSame(){
        MovieDto same = new MovieDto("title","genre",60);

        assertEquals(same,underTest);
        assertEquals(same.hashCode(), underTest.hashCode());
    }
    @Test
    void testEqualsWithNotSame(){
        MovieDto notSame = new MovieDto("title2","genre",30);

        assertNotEquals(notSame,underTest);
        assertNotEquals(notSame.hashCode(), underTest.hashCode());
    }

}
