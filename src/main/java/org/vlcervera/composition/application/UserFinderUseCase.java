package org.vlcervera.composition.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.vlcervera.composition.application.response.UserResponse;
import org.vlcervera.composition.domain.model.User;
import org.vlcervera.composition.domain.port.UserFinderPort;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class UserFinderUseCase {

    private final UserFinderPort userFinderAdapter;

    public UserResponse find(UUID userId) {
        User user = userFinderAdapter.find(userId);
        return UserResponse.of(user);
    }
}
