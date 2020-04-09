package org.vlcervera.composition.domain.port;

import org.vlcervera.composition.domain.model.User;

import java.util.UUID;

public interface UserFinderPort {
    User find(UUID userId);
}
