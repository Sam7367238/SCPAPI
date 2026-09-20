package org.playground.scpapi.user;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserSetupRequest(
        @NotBlank(message = "Please fill this in")
        String email,
        @NotBlank(message = "Please fill this in")
        @Length(min = 8, max = 20, message = "This must be between 8 and 20 characters")
        String password
) {
}
