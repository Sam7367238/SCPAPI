package org.playground.scpapi.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileRepository profileRepository;

    public UserDto createUser(RegisterUserRequest request) {
        var user = userMapper.toEntity(request);

//        user.setCreated(LocalDateTime.now());

        Profile profile = new Profile();
        user.setProfile(profile);

        profileRepository.save(profile);

        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public void enableUserPassword(String email, String password) {
        var user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        if (user.getPassword() != null) {
            throw new AccessDeniedException("You are not authorized to perform this operation");
        }

        user.setPassword(password);

        userRepository.save(user);
    }
}
