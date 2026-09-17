package org.playground.scpapi.user;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        UUID uuid,
        String name,
        String email,
        byte clearanceLevel,
        LocalDateTime created) {
}
