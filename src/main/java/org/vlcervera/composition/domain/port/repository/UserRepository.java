package org.vlcervera.composition.domain.port.repository;

import org.vlcervera.composition.domain.User;

import java.util.UUID;

public interface UserRepository {
    User findById(UUID userId);
}
