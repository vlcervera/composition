package org.vlcervera.version.domain.port;

import org.vlcervera.version.domain.model.User;

import java.util.UUID;

public interface UserFinderPort {
    User find(UUID userId);
}
