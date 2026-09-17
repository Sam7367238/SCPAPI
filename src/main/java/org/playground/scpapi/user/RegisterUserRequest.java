package org.playground.scpapi.user;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record RegisterUserRequest(
        @NotBlank(message = "Please fill this in")
        @Length(max = 150, message = "A name can only contain up to 150 characters")
        String name,
        
        @NotBlank(message = "Please fill this in")
        @Email(message = "This must be an email")
        String email,
        
        @NotBlank(message = "Please fill this in")
        String phoneNumber,

        @Max(value = 3, message = "This cannot be higher than 3")
        int clearanceLevel,
        
        UUID department,
        
        @NotNull(message = "Please fill this in")
        int buildingNumber,

        @Length(min = 3, max = 100, message = "This must be between 3 and 100 characters")
        String district,

        @NotBlank(message = "Please fill this in")
        @Length(min = 3, max = 100, message = "This must be between 3 and 100 characters")
        String street,

        @NotBlank(message = "Please fill this in")
        @Length(min = 3, max = 100, message = "This must be between 3 and 100 characters")
        String city,

        @Length(min = 3, max = 100, message = "This must be between 3 and 100 characters")
        String stateOrProvince,
        
        @NotNull(message = "Please fill this in")
        @Digits(integer = 5, fraction = 0, message = "This cannot be longer than 5 digits")
        int postalCode,
        
        @NotBlank(message = "Please fill this in")
        @Length(max = 5, message = "This cannot be longer than 5 characters")
        String country
) {
}
