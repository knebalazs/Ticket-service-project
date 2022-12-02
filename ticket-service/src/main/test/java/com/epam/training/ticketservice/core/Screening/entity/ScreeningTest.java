package com.epam.training.ticketservice.core.Screening.entity;

import com.epam.training.ticketservice.core.screening.presistence.entity.Screening;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ScreeningTest {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Screening underTest = new Screening("title","name", dateFormat.parse("2021-03-14 16:00"));

    public ScreeningTest() throws ParseException {
    }

    @Test
    void testNotEqualsWithEmpty(){
        Screening empty = new Screening();

        assertNotEquals(empty,underTest);
        assertNotEquals(empty.hashCode(), underTest.hashCode());
    }

    @Test
    void testEqualsWithSame() throws ParseException {
        Screening same = new Screening("title","name",dateFormat.parse("2021-03-14 16:00"));

        assertEquals(same,underTest);
        assertEquals(same.hashCode(), underTest.hashCode());
    }
    @Test
    void testEqualsWithNotSame(){
        Screening notSame = new Screening("title2","name2",new Date());

        assertNotEquals(notSame,underTest);
        assertNotEquals(notSame.hashCode(), underTest.hashCode());
    }

}
