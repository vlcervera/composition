package org.vlcervera.composition.domain.port;

import org.vlcervera.composition.domain.vobject.UserCompany;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface UserCompanyFinderPort {
    CompletableFuture<UserCompany> find(UUID uuid);
}


