package org.playground.scpapi.user;

import lombok.RequiredArgsConstructor;
import org.playground.scpapi.common.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileRepository profileRepository;
    private final EmailService emailService;
    private final RestorationTokenRepository restorationTokenRepository;

    @Value("${spring.restoration-tokens.minutes}")
    private int restorationTokenMinutes;

    public UserDto createUser(RegisterUserRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new DuplicateUserException();
        }

        var user = userMapper.toEntity(request);

//        user.setCreated(LocalDateTime.now());

        Profile profile = new Profile();
        user.setProfile(profile);

        profileRepository.save(profile);

        userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Deprecated(since = "0.5.0", forRemoval = true)
    public void enableUserPassword(String email, String password) {
        var user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        if (user.getPassword() != null) {
            throw new AccessDeniedException("You are not authorized to perform this operation");
        }

        user.setPassword(password);

        userRepository.save(user);
    }

    public void sendPasswordResetEmail(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        var restorationTokenBuilder = new RestorationToken.RestorationTokenBuilder();
        var restorationToken = restorationTokenBuilder
                .user(user)
                .activated(false)
                .purpose(RestorationTokenType.PASSWORD_RESET)
                .expiration(LocalDateTime.now().plusMinutes(restorationTokenMinutes))
                .created(LocalDateTime.now())
                .build();

        restorationTokenRepository.save(restorationToken);

        var uri = UriComponentsBuilder.newInstance()
                .path("/users/password-reset-email")
                .queryParam("token", restorationToken.getUuid())
                .toUriString();

        emailService.sendEmail(email, "Password Reset", uri);
    }

    public void resetPasswordWithToken(UUID tokenId, String newPassword) {
        var token = restorationTokenRepository.findById(tokenId).orElseThrow(() -> new AccessDeniedException("The token is invalid"));

        if (token.isActivated()) {
            throw new AccessDeniedException("This token has already been used");
        }

        if (token.isExpired()) {
            throw new ExpiredTokenException();
        }

        token.setActivated(true);

        restorationTokenRepository.save(token);

        var user = userRepository.findById(token.getUserUuid()).orElseThrow(UserNotFoundException::new);

        user.setPassword(newPassword);

        userRepository.save(user);
    }
}
