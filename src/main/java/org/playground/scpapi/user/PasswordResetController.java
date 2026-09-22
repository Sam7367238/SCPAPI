package org.playground.scpapi.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/password-resets")
@AllArgsConstructor
class PasswordResetController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, String>> initiatePasswordReset(@Valid @RequestBody ResetPasswordRequest request) {
        userService.sendPasswordResetEmail(request.email());

        return ResponseEntity.ok(Map.of("message", "A verification email has been sent"));
    }

    @PostMapping("/{token}")
    public void executePasswordReset(@PathVariable(name = "token") UUID tokenId, @Valid @RequestBody NewPasswordRequest request) {
        if (!request.newPassword().equals(request.repeatPassword())) {
            throw new NonMatchingPasswordsException();
        }

        userService.resetPasswordWithToken(tokenId, request.newPassword());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> userNotFoundException() {
        return ResponseEntity.ok(Map.of("message", "A verification email has been sent"));
    }
}
