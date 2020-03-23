package org.vlcervera.composition.domain.port;

import org.vlcervera.composition.domain.vobject.UserEmail;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface UserEmailFinderPort {
    CompletableFuture<UserEmail> find(UUID uuid);
}
