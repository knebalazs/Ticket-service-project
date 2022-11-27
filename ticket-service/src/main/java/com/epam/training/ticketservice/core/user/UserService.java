package com.epam.training.ticketservice.core.user;


public interface UserService {
    User loginAdmin(String username, String password);

    User logoutAdmin();

    User describeUser();

}
