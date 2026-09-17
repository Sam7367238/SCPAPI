package org.playground.scpapi.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Digits;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record RegisterUserRequest(
        @NotNull
        @Size(min = 5, max = 150)
        String name,
        
        @NotNull
        @Email
        String email,
        
        @NotNull
        String phoneNumber,
        
        @Min(1)
        @Max(3)
        Byte clearanceLevel,
        
        UUID department,
        
        @NotNull
        int buildingNumber,

        @Length(min = 3, max = 100)
        String district,

        @NotNull
        @Length(min = 3, max = 100)
        String street,

        @NotNull
        @Length(min = 3, max = 100)
        String city,

        @NotNull
        @Length(min = 3, max = 100)
        String stateOrProvince,
        
        @NotNull
        @Digits(integer = 5, fraction = 0)
        int postalCode,
        
        @NotNull
        @Size(min = 1, max = 5)
        String country
) {
}
