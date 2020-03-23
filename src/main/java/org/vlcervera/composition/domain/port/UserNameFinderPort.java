package org.vlcervera.composition.domain.port;

import org.vlcervera.composition.domain.vobject.UserName;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface UserNameFinderPort {
    CompletableFuture<UserName> find(UUID uuid);
}
