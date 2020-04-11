package org.vlcervera.version.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.vlcervera.version.application.response.UserResponse;
import org.vlcervera.version.domain.model.User;
import org.vlcervera.version.domain.port.UserFinderPort;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class UserFinderUseCase {

    private final UserFinderPort userFinderPort;

    public UserResponse find(UUID userId) {
        User user = userFinderPort.find(userId);
        return UserResponse.of(user);
    }
}
