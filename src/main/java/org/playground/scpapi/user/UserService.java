package org.playground.scpapi.user;

import lombok.AllArgsConstructor;
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
}
