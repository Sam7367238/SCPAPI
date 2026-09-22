package org.playground.scpapi.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.playground.scpapi.common.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> registerUser(
            @Valid @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        var dto = userService.createUser(request);

        var uri = uriBuilder.path("/users/{id}").buildAndExpand(dto.uuid()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping("/password-reset")
    public ResponseEntity<Map<String, String>> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        userService.sendPasswordResetEmail(request.email());

        return ResponseEntity.ok(Map.of("message", "A verification email has been sent"));
    }

    @PostMapping("/password-reset-email")
    public void resetPasswordEmail(@RequestParam(name = "token") UUID tokenId, @Valid @RequestBody NewPasswordRequest request) {
        if (!request.newPassword().equals(request.repeatPassword())) {
            throw new NonMatchingPasswordsException();
        }

        userService.resetPasswordWithToken(tokenId, request.newPassword());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> handleAccessDeniedException(AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ErrorDto> handleExpiredTokenException(ExpiredTokenException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(NonMatchingPasswordsException.class)
    public ResponseEntity<ErrorDto> handleNonMatchingPasswordsException(NonMatchingPasswordsException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> userNotFoundException() {
        return ResponseEntity.ok(Map.of("message", "A verification email has been sent"));
    }
}
