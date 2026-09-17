package org.playground.scpapi.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
class UserController {
    private final UserService userService;

    @PostMapping
    public void registerUser(@Valid @RequestBody RegisterUserRequest request) {
        userService.createUser(request);
    }
}
