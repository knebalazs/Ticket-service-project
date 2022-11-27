package com.epam.training.ticketservice.core.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Role role;

    public User(Role role) {
        this.role = role;
    }

    public enum Role {
        ADMIN,
        USER
    }
}
