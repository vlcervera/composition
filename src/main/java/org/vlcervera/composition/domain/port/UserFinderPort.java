package org.vlcervera.composition.domain.port;

import org.vlcervera.composition.domain.User;

import java.util.UUID;

public interface UserFinderPort {
    User find(UUID userId);
}
