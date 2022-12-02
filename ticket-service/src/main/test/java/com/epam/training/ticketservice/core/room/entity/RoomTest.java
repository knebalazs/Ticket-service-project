package com.epam.training.ticketservice.core.room.entity;

import com.epam.training.ticketservice.core.room.presistence.entity.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RoomTest {
    private Room underTest = new Room("name", 5, 10);

    @Test
    void testNotEqualsWithEmpty(){
        Room empty = new Room();

        assertNotEquals(empty,underTest);
        assertNotEquals(empty.hashCode(), underTest.hashCode());
    }

    @Test
    void testEqualsWithSame(){
        Room same = new Room("name",5,10);

        assertEquals(same,underTest);
        assertEquals(same.hashCode(), underTest.hashCode());
    }
    @Test
    void testEqualsWithNotSame(){
        Room notSame = new Room("name2",10,10);

        assertNotEquals(notSame,underTest);
        assertNotEquals(notSame.hashCode(), underTest.hashCode());
    }

}
