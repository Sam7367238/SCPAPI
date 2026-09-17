package org.playground.scpapi.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto createUser(RegisterUserRequest request) {
        var user = userMapper.toEntity(request);

        user.setCreated(LocalDateTime.now());

        userRepository.save(user);

        return userMapper.toDto(user);
    }
}
