package org.playground.scpapi.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest(
        @Email
        @NotBlank(message = "Please fill this in")
        String email
) {
}
