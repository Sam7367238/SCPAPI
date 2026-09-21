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

//    @PostMapping("/setup")
//    public ResponseEntity<Void> setupUser(@Valid @RequestBody UserSetupRequest request) {
//        userService.enableUserPassword(request.email(), request.password());
//
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/password-reset")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        userService.sendPasswordResetEmail(request.email());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleAccessDeniedException(AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> userNotFoundException() {
        return ResponseEntity.ok(Map.of("message", "A verification email has been sent"));
    }
}
