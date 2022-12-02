package com.epam.training.ticketservice.core.user;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceImplTest {

    private final UserServiceImpl underTest = new UserServiceImpl();

    @Test
    void testLoginAdminShouldChangeRoleToAdminWhenUsernameAndPasswordAreCorrect() {
        //Given
        String username = "admin";
        String password = "admin";
        User expceted = new User(User.Role.ADMIN);
        //When
        User actual = underTest.loginAdmin(username, password);
        //Then
        assertEquals(expceted,actual);
    }
    @Test
    void testLoginAdminShouldNotChangeRoleToAdminWhenUsernameAndPasswordAreIncorrect() {
        //Given
        String username = "admin";
        String password = "wrong";
        User expceted = new User(User.Role.USER);
        //When
        User actual = underTest.loginAdmin(username, password);
        //Then
        assertEquals(expceted,actual);
    }
    /*
    @Test
    void testLogoutAdminShouldChangeRoleToUser() {
        //Given
        User expceted = new User(User.Role.USER);
        //When
        User actual = underTest.logoutAdmin();
        //Then
        assertEquals(expceted,actual);
    }

     */
    @Test
    void testDescribeUserShouldReturnAdminUser() {
        //Given
        User expceted = new User(User.Role.ADMIN);
        //When
        underTest.loginAdmin("admin","admin");
        User actual = underTest.describeUser();
        //Then
        assertEquals(expceted,actual);
    }
}
