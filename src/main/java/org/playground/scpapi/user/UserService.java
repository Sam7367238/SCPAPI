package org.playground.scpapi.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void createUser(RegisterUserRequest request) {
        var user = userMapper.toEntity(request);

        log.info(user.toString());
    }
}
