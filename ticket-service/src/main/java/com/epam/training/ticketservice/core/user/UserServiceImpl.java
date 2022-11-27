package com.epam.training.ticketservice.core.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    User loggedInUser = new User(User.Role.USER);

    @Override
    public User loginAdmin(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            loggedInUser = new User(User.Role.ADMIN);
        }
        return describeUser();
    }

    @Override
    public User logoutAdmin() {
        User previousUser = loggedInUser;
        loggedInUser = new User(User.Role.USER);
        return previousUser;
    }

    @Override
    public User describeUser() {
        return loggedInUser;
    }
}
