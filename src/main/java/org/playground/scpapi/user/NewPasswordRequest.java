package org.playground.scpapi.user;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record NewPasswordRequest(
        @NotBlank(message = "Please fill this in")
        @Length(min = 8, max = 255)
        String newPassword,

        @NotBlank(message = "Please fill this in")
        @Length(min = 8, max = 255)
        String repeatPassword
) {
}
