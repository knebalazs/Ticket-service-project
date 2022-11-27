package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.user.User;
import com.epam.training.ticketservice.core.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class AdminCommand {
    private final UserService userService;

    @ShellMethod(key = "sign out", value = "Admin logout")
    public String logoutAdmin() {
        User user = userService.logoutAdmin();
        if (user.getRole().equals(User.Role.USER)) {
            return "You are not signed in";
        }
        return "admin is logged out!";
    }

    @ShellMethod(key = "sign in privileged", value = "Admin Login")
    public String loginAdmin(String username, String password) {
        User user = userService.loginAdmin(username, password);
        if (user.getRole().equals(User.Role.USER)) {
            return "Login failed due to incorrect credentials";
        }
        return "signed in privileged admin admin";

    }

    @ShellMethod(key = "describe account", value = "Get Admin information")
    public String describeAdmin() {
        User user = userService.describeUser();
        if (user.getRole().equals(User.Role.USER)) {
            return "You are not signed in";
        }
        return "Signed in with privileged account '" + user.getRole().toString().toLowerCase() + "'";
    }

}
