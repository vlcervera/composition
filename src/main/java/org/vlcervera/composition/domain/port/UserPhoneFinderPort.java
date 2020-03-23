package org.vlcervera.composition.domain.port;

import org.vlcervera.composition.domain.vobject.UserPhone;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface UserPhoneFinderPort {
    CompletableFuture<UserPhone> find(UUID uuid);
}
